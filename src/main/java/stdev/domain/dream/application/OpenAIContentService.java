package stdev.domain.dream.application;

import stdev.domain.dream.presentation.dto.response.ContentGenerationResponse;

import java.util.concurrent.CompletableFuture;

public interface OpenAIContentService {
    CompletableFuture<ContentGenerationResponse> generateContent(String topic);

}