package stdev.domain.lucky.application;

import stdev.domain.lucky.presentation.dto.response.LuckyGenerationResponse;

public interface OpenAILuckyService {
    LuckyGenerationResponse generateLucky(String topic);
}
