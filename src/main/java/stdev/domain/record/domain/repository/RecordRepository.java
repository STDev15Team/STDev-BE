package stdev.domain.record.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stdev.domain.record.domain.entity.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
