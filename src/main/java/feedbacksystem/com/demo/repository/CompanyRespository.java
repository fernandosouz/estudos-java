package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Company;
import feedbacksystem.com.demo.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRespository extends CrudRepository<Company, Long> {

}
