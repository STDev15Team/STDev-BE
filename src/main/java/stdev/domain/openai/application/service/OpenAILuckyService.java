package stdev.domain.openai.application.service;

import stdev.domain.openai.presentation.dto.response.LuckyGenerationResponse;

public interface OpenAILuckyService {
    LuckyGenerationResponse generateLucky(String topic);
}
