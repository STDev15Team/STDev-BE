package stdev.domain.openai.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import stdev.domain.openai.application.service.OpenAIContentService;
import stdev.domain.openai.presentation.dto.request.OpenAIImageRequest;
import stdev.domain.openai.presentation.dto.request.OpenAITextRequest;
import stdev.domain.openai.presentation.dto.response.ContentGenerationResponse;
import stdev.domain.openai.presentation.dto.response.OpenAIImageResponse;
import stdev.domain.openai.presentation.dto.response.OpenAITextResponse;
import stdev.global.infra.feignclient.OpenAIImageFeignClient;
import stdev.global.infra.feignclient.OpenAITextFeignClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenAIContentServiceImpl implements OpenAIContentService {

    private final OpenAITextFeignClient openAITextFeignClient;
    private final OpenAIImageFeignClient openAIImageFeignClient;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.text.model}")
    private String textModel;

    @Value("${openai.image.model}")
    private String imageModel;

    @Async
    @Override
    public CompletableFuture<ContentGenerationResponse> generateContent(String topic) {
        log.info("Generating content for topic: {}", topic);

        // 사용자 입력 정리
        String cleanedTopic = cleanUserInput(topic);

        // 병렬로 텍스트와 이미지 생성
        CompletableFuture<String> textFuture = generateText(cleanedTopic);
        CompletableFuture<String> imageFuture = generateImage(cleanedTopic);

        // 두 작업이 모두 완료될 때까지 기다림
        return CompletableFuture.allOf(textFuture, imageFuture)
                .thenApply(v -> {
                    try {
                        String text = textFuture.get();
                        String imageUrl = imageFuture.get();

                        return new ContentGenerationResponse(text, imageUrl);
                    } catch (Exception e) {
                        log.error("Error combining text and image results: {}", e.getMessage(), e);
                        throw new RuntimeException("Failed to generate content", e);
                    }
                });
    }

    private String cleanUserInput(String input) {
        return input
                .replaceAll("카드뉴스", "")
                .replaceAll("생성해줘", "")
                .replaceAll("만들어줘", "")
                .replaceAll("관한", "")
                .replaceAll("관련", "")
                .replaceAll("에", "")
                .trim();
    }

    private CompletableFuture<String> generateText(String topic) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 텍스트 생성 프롬프트 설정
                String prompt = createTextPrompt(topic);

                List<OpenAITextRequest.Message> messages = new ArrayList<>();
                messages.add(OpenAITextRequest.Message.builder()
                        .role("system")
                        .content("당신은 주제에 맞는 짧고 임팩트 있는 카드뉴스 문구를 생성하는 전문가입니다.")
                        .build());
                messages.add(OpenAITextRequest.Message.builder()
                        .role("user")
                        .content(prompt)
                        .build());

                OpenAITextRequest request = OpenAITextRequest.builder()
                        .model(textModel)
                        .messages(messages)
                        .temperature(0.7)
                        .maxTokens(300)
                        .build();

                OpenAITextResponse response = openAITextFeignClient.generateText(
                        "Bearer " + apiKey,
                        request
                );

                if (response.getChoices() != null && !response.getChoices().isEmpty()) {
                    String content = response.getChoices().get(0).getMessage().getContent();
                    log.info("Generated text: {}", content);
                    return content;
                } else {
                    log.error("No text generated from OpenAI API");
                    return "내용을 생성할 수 없습니다.";
                }
            } catch (Exception e) {
                log.error("Error generating text: {}", e.getMessage(), e);
                return "내용 생성 중 오류가 발생했습니다.";
            }
        });
    }

    private String createTextPrompt(String topic) {
        return String.format(
                "다음 주제에 대한 카드뉴스용 문구를 만들어주세요: '%s'.\n" +
                        "다음 형식으로 작성해주세요:\n" +
                        "1. 제목: 주의를 끌 수 있는 짧은 제목\n" +
                        "2. 주요 내용 (3-5개 문장): 핵심 정보를 간결하고 명확하게\n" +
                        "3. 결론: 행동을 유도하는 마무리 문장\n\n" +
                        "모든 내용은 친근하고 직관적인 언어로 작성해주세요. 전문 용어는 최소화하고, 일반인도 이해하기 쉽게 작성해주세요.",
                topic
        );
    }

    private CompletableFuture<String> generateImage(String topic) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 이미지 생성 프롬프트 설정
                String imagePrompt = createImagePrompt(topic);

                OpenAIImageRequest request = OpenAIImageRequest.builder()
                        .model(imageModel)
                        .prompt(imagePrompt)
                        .n(1)
                        .size("1024x1024")
                        .build();

                OpenAIImageResponse response = openAIImageFeignClient.generateImage(
                        "Bearer " + apiKey,
                        request
                );

                if (response.getData() != null && !response.getData().isEmpty()) {
                    String imageUrl = response.getData().get(0).getUrl();
                    log.info("Generated image URL: {}", imageUrl);
                    return imageUrl;
                } else {
                    log.error("No image data returned from OpenAI API");
                    return null;
                }
            } catch (Exception e) {
                log.error("Error generating image: {}", e.getMessage(), e);
                return null;
            }
        });
    }

    private String createImagePrompt(String topic) {
        return String.format(
                "Topic Overview: " +
                        "- Goal: Create a visually clear and expressive illustration about '%s', focusing on making the topic understandable at a glance. " +
                        "Role: " +
                        "- You are an expert illustrator specializing in creating simple and expressive visuals that effectively communicate ideas. " +
                        "Visual Elements: " +
                        "- Include people with expressive faces to reflect the emotions of '%s'. " +
                        "- Add symbolic elements closely related to '%s' to make the concept immediately recognizable. " +
                        "Design Guidelines: " +
                        "- Use a clean, minimal style with soft, neutral colors. " +
                        "- Avoid clutter and ensure the main idea stands out clearly. " +
                        "- Exclude any text in the illustration, focusing solely on visual representation.",
                topic, topic, topic
        );
    }
}