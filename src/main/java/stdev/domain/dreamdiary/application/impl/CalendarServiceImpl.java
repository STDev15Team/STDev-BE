package stdev.domain.dreamdiary.application.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stdev.domain.dreamdiary.application.CalendarService;
import stdev.domain.dreamdiary.domain.entity.DreamDiary;
import stdev.domain.dreamdiary.domain.repository.DreamDiaryRepository;
import stdev.domain.dreamdiary.presentation.dto.request.CalendarRequest;
import stdev.domain.dreamdiary.presentation.dto.response.CalendarResponse;
import stdev.domain.record.domain.entity.Record;
import stdev.domain.record.domain.repository.RecordRepository;
import stdev.domain.user.domain.entity.User;
import stdev.domain.user.domain.repository.UserRepository;
import stdev.domain.user.infra.exception.UserNotFoundException;

import java.time.Duration;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {
    private final RecordRepository recordRepository;

    private final DreamDiaryRepository dreamDiaryRepository;
    private final UserRepository userRepository;

    @Override
    public List<CalendarResponse> getMonth(String userId, CalendarRequest req) {

        List<DreamDiary> diaries = dreamDiaryRepository.findDreamDiariesByUserIdAndMonth(userId, req.year(), req.month());

        Map<Integer, DreamDiary> map = new HashMap<>();
        for (DreamDiary d : diaries) {
            int day = d.getRecord().getDreamDiary().getSleepStart().getDayOfMonth();

            map.put(day, d);
        }

        int daysInMonth = YearMonth.of(req.year(), req.month()).lengthOfMonth();
        List<CalendarResponse> result = new ArrayList<>();

        for (int day = 1; day <= daysInMonth; day++) {
            if (map.containsKey(day)) {
                DreamDiary d = map.get(day);
                Duration duration = Duration.between(d.getSleepStart(), d.getSleepEnd());

                result.add(new CalendarResponse(day, d.getRate(), duration.toHours(), d.getRecord().getId()));
            } else {
                result.add(new CalendarResponse(day, "0", 0L, 0L));
            }
        }

        return result;
    }

    @Override
    public List<CalendarResponse> getDay(String userId) {
        return null;
    }
}
