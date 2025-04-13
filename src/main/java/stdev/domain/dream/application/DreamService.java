package stdev.domain.dream.application;

import org.springframework.web.multipart.MultipartFile;
import stdev.domain.dream.presentation.dto.request.DreamRecordRequest;

import java.io.IOException;

public interface DreamService {

    void dreamPost(String comment, MultipartFile image) throws IOException;
}
