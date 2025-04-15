package stdev.domain.dreamdiary.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import stdev.domain.record.domain.entity.Record;
import stdev.domain.user.domain.entity.Role;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class DreamDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime sleepStart;

    private LocalDateTime sleepEnd;

    private String note;

    private String rate;

    private String title;

    private String content;

    // 꿈 카테고리
    private String category;

    @OneToOne(mappedBy = "dreamDiary")
    private Record record;

    @Builder
    public DreamDiary(LocalDateTime sleepStart, LocalDateTime sleepEnd, String note,
                      String rate, String title, String content, String category) {
        this.sleepStart = sleepStart;
        this.sleepEnd = sleepEnd;
        this.note = note;
        this.rate = rate;
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
