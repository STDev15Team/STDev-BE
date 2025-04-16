package stdev.domain.dreamdiary.application;

import org.springframework.stereotype.Repository;
import stdev.domain.dreamdiary.presentation.dto.request.DiaryPostRequest;
import stdev.domain.dreamdiary.presentation.dto.response.DiaryPostResponse;
import stdev.domain.dreamdiary.presentation.dto.response.SleepRateResponse;

import java.util.List;

public interface DreamDiaryService {
    DiaryPostResponse dreamPost(DiaryPostRequest req, String userId);

    List<SleepRateResponse> sleepRate(String userId);
}
