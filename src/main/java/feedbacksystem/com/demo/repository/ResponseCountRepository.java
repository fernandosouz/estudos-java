package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.ResponseCount;
import feedbacksystem.com.demo.model.responses.chart.WrappedPredefinedResponseInterface;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ResponseCountRepository extends CrudRepository<ResponseCount, Long> {

   //TODO quando der trocar o where para data
    @Query(value="SELECT rc.* FROM response_count rc WHERE rc.predefined_response_id=:id  ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ResponseCount findLastOneByIdOfPredefinedQuestion(@Param("id") Long id);

    @Query(value="SELECT pr.id AS predefinedId,\n" +
            "       sum(rc.count) AS sum,\n" +
            "       pr.description AS predefinedResponseDescription\n" +
            "    FROM\n" +
            "         response_count AS rc\n" +
            "    INNER JOIN\n" +
            "             predefined_response AS pr\n" +
            "                 on rc.predefined_response_id = pr.id\n" +
            "    where\n" +
            "          rc.created_date_time BETWEEN :start AND :end\n" +
            "          AND pr.question_id = :questionId\n" +
            "    GROUP BY\n" +
            "             pr.id,\n" +
            "             pr.description\n" +
            "    ORDER BY pr.id", nativeQuery = true)
    List<WrappedPredefinedResponseInterface> getSumBetweenDatesByQuestion(@Param("questionId") Long questionId, @Param("start") Date start, @Param("end") Date end);
}
