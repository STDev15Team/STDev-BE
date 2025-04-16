package stdev.domain.dreamanalysis.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdev.domain.dreamanalysis.application.OpenAIContentService;
import stdev.domain.dreamanalysis.presentation.dto.request.ContentGenerationRequest;
import stdev.domain.dreamanalysis.presentation.dto.response.DreamCommentResponse;
import stdev.domain.dreamanalysis.presentation.dto.response.DreamImageResponse;
import stdev.domain.dreamanalysis.presentation.dto.response.HeadResponse;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
@Slf4j
public class OpenAIContentController {

    private final OpenAIContentService openAIContentService;

    @PostMapping("/comment")
    public ResponseEntity<DreamCommentResponse> generateComment(@RequestBody ContentGenerationRequest request) {

        return ResponseEntity.ok(openAIContentService.generateComment(request.topic()));
    }

    @PostMapping("/image")
    public ResponseEntity<DreamImageResponse> generateImage(@RequestBody ContentGenerationRequest request) {
        return ResponseEntity.ok(openAIContentService.generateImage(request.topic()));
    }


    @GetMapping("/head")
    public ResponseEntity<HeadResponse> generateHead(@RequestParam String category) {
        return ResponseEntity.ok(openAIContentService.generateHead(category));
    }

}