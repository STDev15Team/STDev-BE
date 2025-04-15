package stdev.domain.dreamanalysis.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdev.domain.dreamanalysis.domain.entity.Dream;

public interface DreamRepository extends JpaRepository<Dream, Long> {
}
