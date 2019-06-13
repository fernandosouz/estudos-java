package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.TextResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.websocket.server.PathParam;
import java.util.Date;

public interface TextResponseRepository extends CrudRepository<TextResponse, Long> {


    @Query(value = "SELECT tr.* FROM text_response tr " +
                        "JOIN question q " +
                            "ON q.id = tr.question_id " +
                        "JOIN company c " +
                            "ON c.id = q.company_id " +
                        "WHERE " +
                            "c.id = :companyId " +
                            "AND q.question_type = 2 " +
                            "AND tr.created_date_time BETWEEN :start AND :end ", nativeQuery = true)
    Long getTextResponseForQuestionsListByCompanyIdBetweenDates(
            @Param("id") Long companyId,
            @Param("start") Date start,
            @Param("end") Date end);
}