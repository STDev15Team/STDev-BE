package stdev.domain.dreamdiary.presentation.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record DiaryGetResponse(


        Long id,
        LocalDateTime sleepStart,

        LocalDateTime sleepEnd,
        // 수면 메모
        String note,

        String rate,

        String title,

        String content,

        // 꿈 카테고리
        List<String> diaryCategory,

        // 특이사항
        String issueDetail,

        // 꿈 분석이 있는지 없는지
        boolean flag

) {
    public static DiaryGetResponse of(Long id,
                                      LocalDateTime sleepStart,

                                      LocalDateTime sleepEnd,

                                      // 수면 메모
                                      String note,

                                      String rate,

                                      String title,

                                      String content,

                                      // 꿈 카테고리
                                      List<String> diaryCategory,

                                      // 특이사항
                                      String issueDetail,
                                      boolean flag) {
        return new DiaryGetResponse(id, sleepStart, sleepEnd, note, rate, title, content, diaryCategory, issueDetail, flag);
    }

}
