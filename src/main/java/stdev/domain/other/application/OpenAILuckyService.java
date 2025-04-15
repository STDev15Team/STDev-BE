package stdev.domain.other.application;

import stdev.domain.other.presentation.dto.response.LuckyGenerationResponse;

public interface OpenAILuckyService {
    LuckyGenerationResponse generateLucky(String topic);
}
