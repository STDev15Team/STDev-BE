package stdev.domain.dreamanalysis.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@DynamicUpdate
public class Dream {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dreamImageUrl;

    @Column(nullable = false)
    private String dreamComment;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;


    @Builder
    public Dream(String dreamImageUrl, String dreamComment) {
        this.dreamImageUrl = dreamImageUrl;
        this.dreamComment = dreamComment;
    }
}
