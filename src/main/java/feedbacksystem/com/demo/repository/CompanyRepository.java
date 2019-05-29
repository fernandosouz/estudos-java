package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
