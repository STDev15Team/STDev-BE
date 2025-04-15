package stdev.domain.dreamanalysis.application.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import stdev.domain.dreamanalysis.application.DreamService;
import stdev.domain.dreamanalysis.domain.entity.Dream;
import stdev.domain.dreamanalysis.domain.repository.DreamRepository;
import stdev.global.config.FileStore;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DreamServiceImpl implements DreamService {
    private final DreamRepository dreamRepository;
    private final FileStore fileStore;


    @Override
    public void dreamPost(String comment, MultipartFile image) throws IOException {
        String imageUrl = fileStore.storeFile(image);
        Dream dream = Dream.builder().dreamImageUrl(imageUrl).dreamComment(comment).build();

        dreamRepository.save(dream);
    }
}
