package stdev.domain.dream.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdev.domain.dream.domain.entity.Dream;

public interface DreamRepository extends JpaRepository<Dream, Long> {
}
