package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {

    @Query(value="SELECT q.* FROM question q WHERE q.company_id=:id", nativeQuery = true)
    List<Question> findAllByCompanyId(@Param("id") Long id);

    @Query(value="SELECT q.* FROM question q " +
            "   WHERE q.id IN :idList      " +
            "   AND   q.question_type = 1   "
            , nativeQuery = true)
    List<Question> findAllByType(@Param("idList") List<Long> idList);



}
