package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.TextResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TextResponseRepository extends CrudRepository<TextResponse, Long> {


    @Query(value = "SELECT tr.* FROM text_response tr " +
                        " INNER JOIN question q " +
                            " ON q.id = tr.question_id " +
                        " INNER JOIN company c " +
                            " ON c.id = q.company_id " +
                        " WHERE " +
                            " c.id = :companyId " +
                            " AND q.question_type = 2 " +
                            " AND tr.created_date_time BETWEEN :start AND :end", nativeQuery = true)
    List<TextResponse> getTextResponseForQuestionsListByCompanyIdBetweenDates(
            @Param("companyId") Long companyId,
            @Param("start") Date start,
            @Param("end") Date end);

}