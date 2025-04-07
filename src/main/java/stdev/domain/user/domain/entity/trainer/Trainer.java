package stdev.domain.user.domain.entity.trainer;

import flowfit.domain.schedule.domain.entity.Schedule;
import flowfit.domain.user.domain.entity.User;
import flowfit.domain.user.domain.entity.trainermember.TrainerMember;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "trainer")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User와 1:1 관계 (Trainer 입장에서 주인 아님)
    @OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User user;

    // 🔽 TrainerMember 양방향 관계
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainerMember> trainerMembers = new ArrayList<>();

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    @Builder
    public Trainer(User user){
        this.user=user;
    }
}
