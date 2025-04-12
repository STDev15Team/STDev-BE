package stdev.domain.openai.application.service;

import stdev.domain.openai.presentation.dto.response.ContentGenerationResponse;
import stdev.domain.openai.presentation.dto.response.LuckyGenerationResponse;

import java.util.concurrent.CompletableFuture;

public interface OpenAIContentService {
    CompletableFuture<ContentGenerationResponse> generateContent(String topic);

}