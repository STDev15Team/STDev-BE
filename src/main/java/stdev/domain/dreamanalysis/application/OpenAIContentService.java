package stdev.domain.dreamanalysis.application;

import stdev.domain.dreamanalysis.presentation.dto.response.ContentGenerationResponse;
import stdev.domain.dreamanalysis.presentation.dto.response.DreamCommentResponse;
import stdev.domain.dreamanalysis.presentation.dto.response.DreamImageResponse;

import java.util.concurrent.CompletableFuture;

public interface OpenAIContentService {
    CompletableFuture<ContentGenerationResponse> generateContent(String topic);


    DreamCommentResponse generateComment(String topic);

    DreamImageResponse generateImage(String topic);

}