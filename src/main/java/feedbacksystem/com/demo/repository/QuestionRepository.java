package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {
}
