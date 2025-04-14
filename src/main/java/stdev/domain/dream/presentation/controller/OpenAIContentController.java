package stdev.domain.dream.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdev.domain.dream.application.OpenAIContentService;
import stdev.domain.dream.presentation.dto.request.ContentGenerationRequest;
import stdev.domain.dream.presentation.dto.response.ContentGenerationResponse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
@Slf4j
public class OpenAIContentController {

    private final OpenAIContentService openAIContentService;

    @PostMapping("/content")
    public ResponseEntity<ContentGenerationResponse> generateContent(@RequestBody ContentGenerationRequest request)
            throws ExecutionException, InterruptedException {
        CompletableFuture<ContentGenerationResponse> contentFuture =
                openAIContentService.generateContent(request.topic());

        ContentGenerationResponse response = contentFuture.get(); // 비동기 결과를 동기적으로 기다림
        return ResponseEntity.ok(response);
    }
}