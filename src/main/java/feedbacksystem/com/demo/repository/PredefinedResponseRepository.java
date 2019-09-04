package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PredefinedResponseRepository extends CrudRepository<PredefinedResponse, Long> {


    @Query(value="SELECT p.* FROM predefined_response p WHERE p.question_id=:id", nativeQuery = true)
    List<PredefinedResponse> findAllByQuestionId(@Param("id") Long id);

}
