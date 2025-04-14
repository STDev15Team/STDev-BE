package stdev.domain.dream.presentation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stdev.domain.dream.application.DreamService;
import stdev.domain.dream.presentation.dto.request.DreamRecordRequest;
import stdev.global.config.FileStore;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/dream")
public class DreamController {

    private final DreamService dreamService;

    @PostMapping
    public void dreamPost(    @RequestPart("comment") String comment,
                              @RequestPart("image") MultipartFile image) throws IOException {

        dreamService.dreamPost(comment, image);
    }

    @PatchMapping
    public void dreamPost2() throws IOException {
        log.info("하이");

    }
}
