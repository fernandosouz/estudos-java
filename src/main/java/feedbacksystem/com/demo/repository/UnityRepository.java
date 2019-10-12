package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.model.Unity;
import feedbacksystem.com.demo.model.responses.chart.DataToChartOptimized;
import feedbacksystem.com.demo.model.responses.textresponse.IdNameOfUnity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UnityRepository extends CrudRepository<Unity, Long> {

    @Query(value="SELECT u.id, u.name FROM unity u WHERE company_id = :companyId", nativeQuery = true)
    List<IdNameOfUnity> findAllUnitsByCompanyId(@Param("companyId") Long companyId);

    @Query(value="SELECT u.* FROM unity u WHERE company_id = :companyId", nativeQuery = true)
    List<Unity> findunitsByCompanyId(@Param("companyId") Long companyId);

    @Query(value=" SELECT\n" +
            "    u.id                    AS unityId,\n" +
            "    q.description           AS questionDescription,\n" +
            "    q.id                    AS questionId,\n" +
            "    pr.description          AS predefinedResponseDescription,\n" +
            "    pr.id                   AS predefinedId,\n" +
            "    pr.is_positive_response AS isPositiveResponse,\n" +
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
            "    q.id = :questionId \n" +
            "    AND q.question_type = 1\n" +
            "    AND q.show_on_dash_board = true\n" +
            " GROUP BY\n" +
            "    u.id,\n" +
            "    q.description,\n" +
            "    q.id,\n" +
            "    pr.description,\n" +
            "    pr.id ", nativeQuery = true)
    List<DataToChartOptimized> getSumBetweenDatesByQuestion(@Param("questionId") Long questionId, @Param("start") Date start, @Param("end") Date end);



}
