package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Identification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentificationRepository extends CrudRepository<Identification, Long> {
}
