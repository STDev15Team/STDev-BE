
package stdev.domain.openai.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stdev.domain.openai.application.service.OpenAILuckyService;
import stdev.domain.openai.presentation.dto.request.LuckyGenerationRequest;
import stdev.domain.openai.presentation.dto.response.LuckyGenerationResponse;

@RestController
@RequestMapping("/api/openai")
@RequiredArgsConstructor
@Slf4j
public class OpenAILuckyController {

    private final OpenAILuckyService openAILuckyService;
    @PostMapping("/lucky")
    public ResponseEntity<LuckyGenerationResponse> generateContent(@RequestBody LuckyGenerationRequest request) {
        return ResponseEntity.ok(openAILuckyService.generateLucky(request.topic()));
    }
}