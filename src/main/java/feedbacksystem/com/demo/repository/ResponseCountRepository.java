package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.ResponseCount;
import feedbacksystem.com.demo.model.responses.chart.DataToChartOptimized;
import feedbacksystem.com.demo.model.responses.chart.WrappedPredefinedResponse;
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
            "                 on rc.predefined_response_id = pr.id\n"
            +"    INNER JOIN\n" +
            "             question AS q\n" +
            "                 on q.id = pr.question_id\n" +
            "    where\n" +
            "          rc.created_date_time BETWEEN :start AND :end\n" +
            "          AND pr.question_id = :questionId\n" +
            "          AND q.question_type = 1\n" +
            "          AND q.show_on_dash_board = true\n" +
            "    GROUP BY\n" +
            "             pr.id,\n" +
            "             pr.description\n" +
            "    ORDER BY pr.id", nativeQuery = true)
    List<WrappedPredefinedResponse> getSumBetweenDatesByQuestion(@Param("questionId") Long questionId, @Param("start") Date start, @Param("end") Date end);


    @Query(value=" SELECT\n" +
            "    u.id                    AS unityId,\n" +
            "    q.description           AS questionDescription,\n" +
            "    q.id                    AS questionId,\n" +
            "    pr.description          AS predefinedResponseDescription,\n" +
            "    pr.id                   AS predefinedId,\n" +
            "    CASE\n" +
            "        WHEN (sum(rc.count) ISNULL)\n" +
            "            THEN 0\n" +
            "            ELSE sum(rc.count)\n" +
            "    END                     AS sum\n" +
            " from predefined_response pr\n" +
            "    LEFT JOIN response_count rc on (pr.id = rc.predefined_response_id AND rc.created_date_time BETWEEN :start AND :end)\n" +
            "    INNER JOIN question q on pr.question_id = q.id\n" +
            "    INNER JOIN unity u on q.unity_id = u.id\n" +
            " WHERE\n" +
            "    u.id = :unityId \n" +
            "    AND q.question_type = 1\n" +
            "    AND q.show_on_dash_board = true\n" +
            " GROUP BY\n" +
            "    u.id,\n" +
            "    q.description,\n" +
            "    q.id,\n" +
            "    pr.description,\n" +
            "    pr.id ", nativeQuery = true)
    List<DataToChartOptimized> getSumBetweenDatesByUnity(@Param("unityId") Long unityId, @Param("start") Date start, @Param("end") Date end);
}
