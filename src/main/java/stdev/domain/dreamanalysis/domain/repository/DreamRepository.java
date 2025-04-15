package stdev.domain.dreamanalysis.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdev.domain.dreamanalysis.domain.entity.DreamAnalysis;

public interface DreamRepository extends JpaRepository<DreamAnalysis, Long> {
}
