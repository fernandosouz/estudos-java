package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.model.responses.textresponse.TextResponseWithQuestion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {

    @Query(value="SELECT q.* FROM question q WHERE q.unity_id=:id AND q.question_type = 1", nativeQuery = true)
    List<Question> findAllByUnityId(@Param("id") Long id);

    @Query(value="SELECT q.* FROM question q WHERE q.unity_id=:id AND q.question_type = 1 AND q.show_on_dash_board = true ", nativeQuery = true)
    List<Question> findAllByUnityIdAndFilter(@Param("id") Long id);

    @Query(value="SELECT q.* FROM question q WHERE q.unity_id=:id AND q.show_on_feed_back_app = true", nativeQuery = true)
    List<Question> findAllByUnityIdToApp(@Param("id") Long id);

    @Query(value="SELECT q.* FROM question q " +
            "   WHERE q.id IN :idList      " +
            "   AND   q.question_type = 1  " +
            "   AND q.show_on_dash_board = true " +
            "   ORDER BY q.id "
            , nativeQuery = true)
    List<Question> findAllByType(@Param("idList") List<Long> idList);


    @Query(value = "SELECT q.description AS questionDescription, " +
            "       q.id AS questionId," +
            "       tr.answer AS textResponseDescription, " +
            "       tr.created_date_time AS textResponseDate " +
            " FROM question q " +
            " INNER JOIN text_response tr " +
            " ON q.id = tr.question_id " +
            " INNER JOIN unity c " +
            " ON c.id = q.unity_id " +
            " WHERE " +
            " c.id = :unityId " +
            " AND q.question_type = 2 " +
            " AND tr.created_date_time BETWEEN :start AND :end", nativeQuery = true)
    List<TextResponseWithQuestion> getQuestionToTextResponse(
            @Param("unityId") Long unityId,
            @Param("start") Date start,
            @Param("end") Date end);


}
