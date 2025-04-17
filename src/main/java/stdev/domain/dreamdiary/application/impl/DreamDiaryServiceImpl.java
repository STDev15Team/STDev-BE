package stdev.domain.dreamdiary.application.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stdev.domain.dreamanalysis.domain.entity.DreamAnalysis;
import stdev.domain.dreamanalysis.domain.repository.DreamAnalysisRepository;
import stdev.domain.dreamdiary.application.DreamDiaryService;
import stdev.domain.dreamdiary.domain.entity.DiaryCategory;
import stdev.domain.dreamdiary.domain.entity.DreamDiary;
import stdev.domain.dreamdiary.domain.repository.DiaryCategoryRepository;
import stdev.domain.dreamdiary.domain.repository.DreamDiaryRepository;
import stdev.domain.dreamdiary.infra.exception.InfromationDiaryException;
import stdev.domain.dreamdiary.presentation.dto.request.DiaryPostRequest;
import stdev.domain.dreamdiary.presentation.dto.response.DiaryGetResponse;
import stdev.domain.dreamdiary.presentation.dto.response.DiaryPostResponse;
import stdev.domain.dreamdiary.presentation.dto.response.SleepRateResponse;
import stdev.domain.record.domain.entity.Record;
import stdev.domain.record.domain.repository.RecordRepository;
import stdev.domain.user.domain.entity.User;
import stdev.domain.user.domain.repository.UserRepository;
import stdev.domain.user.infra.exception.UserNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DreamDiaryServiceImpl implements DreamDiaryService {
    private final DreamDiaryRepository dreamDiaryRepository;

    private final DreamAnalysisRepository dreamAnalysisRepository;

    private final RecordRepository recordRepository;

    private final UserRepository userRepository;


    private final DiaryCategoryRepository diaryCategoryRepository;


    @Override
    public DiaryPostResponse dreamPost(DiaryPostRequest req, String userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new UserNotFoundException("회원 정보 없음요");
        }

        List<DreamDiary> bySleepStartYearAndMonth = dreamDiaryRepository.findBySleepStartYearAndMonthAndDay(
                req.sleepStart().getYear(), req.sleepStart().getMonthValue(),req.sleepStart().getDayOfMonth());

        if (!bySleepStartYearAndMonth.isEmpty()) {
            throw new InfromationDiaryException();
        }


        if (req.flag()) {
            DreamDiary dreamDiary = DreamDiary.
                    builder().sleepStart(req.sleepStart())
                    .sleepEnd(req.sleepEnd())
                    .note(req.note())
                    .rate(req.rate())
                    .title(req.title())
                    .content(req.content())
                    .issueDetail(req.issueDetail())
                    .build();
            DreamAnalysis dreamAnalysis = DreamAnalysis.builder()
                    .flag(true)
                    .dreamCategory(req.dreamCategory())
                    .dreamComment(req.comment())
                    .dreamImageUrl(req.imageUrl())
                    .build();
            Record record = Record.builder()
                    .dreamDiary(dreamDiary)
                    .user(user)
                    .dreamAnalysis(dreamAnalysis)
                    .build();

            DreamDiary diarySave = dreamDiaryRepository.save(dreamDiary);
            diarySave.getId();

            for (String category : req.categories()) {
                DiaryCategory diaryCategory = DiaryCategory.builder().name(category).dreamDiary(diarySave).build();
                diaryCategoryRepository.save(diaryCategory);
            }
            dreamAnalysisRepository.save(dreamAnalysis);
            Record save = recordRepository.save(record);
            return DiaryPostResponse.of(save.getId());

        } else {
            DreamDiary dreamDiary = DreamDiary.
                    builder().sleepStart(req.sleepStart())
                    .sleepEnd(req.sleepEnd())
                    .note(req.note())
                    .rate(req.rate())
                    .title(req.title())
                    .content(req.content())
                    .issueDetail(req.issueDetail())
                    .build();

            Record record = Record.builder()
                    .dreamDiary(dreamDiary)
                    .user(user)
                    .dreamAnalysis(null)
                    .build();
            DreamDiary diarySave = dreamDiaryRepository.save(dreamDiary);

            for (String category : req.categories()) {
                DiaryCategory diaryCategory = DiaryCategory.builder().name(category).dreamDiary(diarySave).build();
                diaryCategoryRepository.save(diaryCategory);
            }
            Record save = recordRepository.save(record);
            return DiaryPostResponse.of(save.getId());
        }
    }

    @Override
    public DiaryGetResponse dreamGet(Long id) {
        Record record = recordRepository.findById(id).orElse(null);
        if (record == null) {
            throw new UserNotFoundException("기록이 없습니다.");
        }
        DreamDiary dreamDiary = record.getDreamDiary();
        List<DiaryCategory> diaryCategories = dreamDiary.getDiaryCategories();

        List<String> list = new ArrayList<>();
        for (DiaryCategory diaryCategory : diaryCategories) {
            list.add(diaryCategory.getName());
        }

        DreamAnalysis dreamAnalysis = record.getDreamAnalysis();
        boolean flag = true; // 꿈 분석 있는지 없는지
        if (dreamAnalysis == null) {
            flag = false;
        }
        return DiaryGetResponse.of(dreamDiary.getId(), dreamDiary.getSleepStart(), dreamDiary.getSleepEnd(), dreamDiary.getNote()
                , dreamDiary.getRate(), dreamDiary.getTitle(), dreamDiary.getContent(), list, dreamDiary.getIssueDetail(), flag);
    }

    @Override
    public List<SleepRateResponse> sleepRate(String userId) {

        // 오늘 ~ 6일 전까지 날짜 리스트 만들기
        List<LocalDate> last7Days = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> LocalDate.now().minusDays(6 - i))
                .collect(Collectors.toList());

        // 가져온 데이터 Map<날짜, DreamDiary>
        List<DreamDiary> diaries = dreamDiaryRepository.findLast7DaysDreamsByUserId(userId);
        Map<LocalDate, DreamDiary> diaryMap = diaries.stream()
                .collect(Collectors.toMap(
                        d -> d.getSleepStart().toLocalDate(),
                        Function.identity()
                ));

        // 응답 리스트 생성
        List<SleepRateResponse> result = new ArrayList<>();
        for (LocalDate date : last7Days) {
            String day = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN); // 요일 ex. "수"
            DreamDiary diary = diaryMap.get(date);
            String rate = (diary != null) ? diary.getRate() : "0";

            result.add(SleepRateResponse.of(day, rate));
        }

        return result;
    }
}
