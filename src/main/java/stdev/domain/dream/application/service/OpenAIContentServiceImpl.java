package stdev.domain.dream.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import stdev.domain.dream.application.OpenAIContentService;
import stdev.domain.dream.domain.entity.Dream;
import stdev.domain.dream.domain.repository.DreamRepository;
import stdev.domain.openai.presentation.dto.request.OpenAIImageRequest;
import stdev.domain.openai.presentation.dto.request.OpenAITextRequest;
import stdev.domain.dream.presentation.dto.response.ContentGenerationResponse;
import stdev.domain.openai.presentation.dto.response.OpenAIImageResponse;
import stdev.domain.openai.presentation.dto.response.OpenAITextResponse;
import stdev.domain.user.infra.exception.UserNotFoundException;
import stdev.global.config.FileStore;
import stdev.global.infra.feignclient.OpenAIImageFeignClient;
import stdev.global.infra.feignclient.OpenAITextFeignClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
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
                        log.info(e.getMessage());
                        throw new UserNotFoundException("생성 실패..");
                    }
                });
    }

    private String cleanUserInput(String input) {
        return input;
//                .replaceAll("카드뉴스", "")
//                .replaceAll("생성해줘", "")
//                .replaceAll("만들어줘", "")
//                .replaceAll("관한", "")
//                .replaceAll("관련", "")
//                .replaceAll("에", "")
//                .trim();
    }

    private CompletableFuture<String> generateText(String topic) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 텍스트 생성 프롬프트 설정
                String prompt = createTextPrompt(topic);

                List<OpenAITextRequest.Message> messages = new ArrayList<>();
                messages.add(OpenAITextRequest.Message.builder()
                        .role("system")
                        .content("You are a dream interpretation expert. Based on psychological knowledge, please analyze the symbols and meanings that appear in the dream.")
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
                "Please analyze the following dream: \"%s\".\n" +
                        "Start your response with **either** '악몽,' or '해몽,' depending on the nature of the dream.\n" +
                        "Then provide a symbolic interpretation, psychological analysis, and a helpful message for real life.\n" +
                        "Your response must be written in Korean and should be between 250 and 300 characters.",
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
                "\"%s\"에 관한 꿈을 고해상도로 시각화한 이미지. " +
                        "꿈의 핵심 감정(공포, 혼란, 기쁨, 신비로움 등)을 시각적으로 자연스럽게 반영하고, " +
                        "비현실적이고 초현실적인 분위기를 가진 장면으로 구성. " +
                        "빛과 그림자, 색채, 인물의 동세와 표정 등을 통해 감정과 분위기를 전달할 것. " +
                        "텍스트나 문구, 로고 등은 절대 포함하지 말 것.",
                topic
        );
    }


}