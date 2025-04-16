package stdev.domain.dreamdiary.domain.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import stdev.domain.dreamdiary.domain.entity.DreamDiary;

import java.util.List;
import java.util.Optional;

@Repository
public interface DreamDiaryRepository extends JpaRepository<DreamDiary, Long> {
    @Query(value = """
    SELECT d.*
    FROM record r
    LEFT JOIN dream_diary d ON r.dream_diary_id = d.id
    WHERE r.user_id = :userId
      AND DATE(r.created_at) BETWEEN CURRENT_DATE - INTERVAL 6 DAY AND CURRENT_DATE
    ORDER BY r.created_at ASC
""", nativeQuery = true)
    List<DreamDiary> findLast7DaysDreamsByUserId(@Param("userId") String userId);
}
