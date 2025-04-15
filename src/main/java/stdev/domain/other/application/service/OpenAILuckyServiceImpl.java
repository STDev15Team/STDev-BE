package stdev.domain.other.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import stdev.domain.other.application.OpenAILuckyService;
import stdev.domain.openai.presentation.dto.request.OpenAITextRequest;
import stdev.domain.other.presentation.dto.response.LuckyGenerationResponse;
import stdev.domain.openai.presentation.dto.response.OpenAITextResponse;
import stdev.domain.user.infra.exception.UserNotFoundException;
import stdev.global.infra.feignclient.OpenAITextFeignClient;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OpenAILuckyServiceImpl implements OpenAILuckyService {

    private final OpenAITextFeignClient openAITextFeignClient;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.text.model}")
    private String textModel;

    @Override
    public LuckyGenerationResponse generateLucky(String topic) {
        log.info("Generating lucky text for topic: {}", topic);

        try {
            // 프롬프트 생성 함수를 호출
            String prompt = createPromptForTopic(topic);

            List<OpenAITextRequest.Message> messages = new ArrayList<>();
            messages.add(OpenAITextRequest.Message.builder()
                    .role("user")
                    .content(prompt)
                    .build());

            OpenAITextRequest request = OpenAITextRequest.builder()
                    .model(textModel)
                    .messages(messages)
                    .temperature(0.7)
                    .maxTokens(1000)
                    .build();

            OpenAITextResponse response = openAITextFeignClient.generateText(
                    "Bearer " + apiKey,
                    request
            );

            if (response.getChoices() != null && !response.getChoices().isEmpty()) {
                String content = response.getChoices().get(0).getMessage().getContent();
                log.info("Generated text: {}", content.substring(0, Math.min(content.length(), 100)) + "...");
                return LuckyGenerationResponse.of(content);
            } else {
                throw new UserNotFoundException("내용을 생성할 수 없습니다.");
            }
        } catch (Exception e) {
            log.error("Error generating text: {}", e.getMessage(), e);
            throw new RuntimeException("텍스트 생성 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 토픽에 맞는 프롬프트를 생성하는 함수
    private String createPromptForTopic(String topic) {
        return String.format(
                "이 주제가 꿈인데 이것에 대한 행운의 메시지를 생성해주세요: '%s'. " +
                        "오늘의 운세처럼 긍정적이고 희망적인 메시지를 200자 이내로 작성해주세요.",
                topic
        );
    }

}
