package stdev.domain.record.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import stdev.domain.dreamanalysis.domain.entity.DreamAnalysis;
import stdev.domain.dreamdiary.domain.entity.DreamDiary;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "dream_diary_id")
    private DreamDiary dreamDiary;


    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "dream_analysis_id")
    private DreamAnalysis dreamAnalysis;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate createdAt;
}
