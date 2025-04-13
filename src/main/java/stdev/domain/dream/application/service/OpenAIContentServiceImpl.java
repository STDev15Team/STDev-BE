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
                        .content("당신은 꿈을 해석하는 전문가입니다. 심리학적 지식을 바탕으로 꿈에 등장하는 상징과 의미를 해석해 주세요. " +
                                "사람들의 꿈에 나타나는 상징, 감정, 상황 등을 분석하여 잠재의식에 담긴 메시지를 찾아내고, " +
                                "이를 일상생활에 적용할 수 있는 통찰력 있는 조언을 제공합니다. " +
                                "전문 용어는 최소화하고 이해하기 쉬운 언어로 설명해 주세요.")
                        .build());
                messages.add(OpenAITextRequest.Message.builder()
                        .role("user")
                        .content(prompt)
                        .build());

                OpenAITextRequest request = OpenAITextRequest.builder()
                        .model(textModel)
                        .messages(messages)
                        .temperature(0.7)
                        .maxTokens(600)
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
                "다음 꿈에 대한 해석을 카드뉴스 형식으로 만들어주세요: '%s'.\n" +
                        "다음 형식으로 작성해주세요:\n" +
                        "1. 제목: 꿈의 핵심을 담은 흥미로운 제목\n" +
                        "2. 꿈의 상징 해석 (3-5개): 꿈에 등장한 주요 요소나 상징의 일반적인 의미\n" +
                        "3. 심리적 의미: 꿈이 나타내는 잠재의식적 메시지나 감정 상태\n" +
                        "4. 현실 적용: 이 꿈이 현실 생활에 주는 메시지나 조언\n\n" +
                        "모든 내용은 친근하고 이해하기 쉬운 언어로 작성해주세요. 전문적인 심리 용어는 최소화하고, 일반인도 쉽게 이해할 수 있게 작성해주세요.",
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
                "꿈 시각화: " +
                        "- 목표: '%s'에 관한 꿈을 시각적으로 명확하고 표현력 있게 일러스트레이션으로 제작하여 한눈에 이해할 수 있도록 합니다. " +
                        "역할: " +
                        "- 당신은 꿈의 이미지와 상징을 시각적으로 표현하는 전문 일러스트레이터입니다. 무의식의 메시지를 시각적으로 효과적으로 전달합니다. " +
                        "시각적 요소: " +
                        "- 꿈 속 '%s'의 감정과 분위기를 표현하는 인물이나 상징적 요소를 포함하세요. " +
                        "- '%s'와 관련된 핵심 상징과 이미지를 포함하여 꿈의 본질을 즉시 인식할 수 있게 하세요. " +
                        "디자인 가이드라인: " +
                        "- 몽환적인 디자인을 사용하세요. " +
                        "- 복잡함을 피하고 꿈의 핵심 메시지가 명확히 드러나도록 하세요. " +
                        "- 텍스트를 포함하지 말고 순수하게 시각적 표현에 집중하세요. " +
                        "- 꿈의 상징적 의미가 시각적으로 전달될 수 있도록 하세요.",
                topic, topic, topic
        );
    }


}