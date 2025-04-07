package stdev.domain.user.domain.repository;

import flowfit.domain.user.domain.entity.trainermember.TrainerMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerMemberRepository extends JpaRepository<TrainerMember, Long> {


}
