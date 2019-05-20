package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.ResponseCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ResponseCountRepository extends CrudRepository<ResponseCount, Long> {

   /* @Query("from ResponseCount a where a.createdDateTime=:date")
    public Iterable<ResponseCount> findByDateAndPredefinedQuestion(@Param("categoryName") LocalDateTime date);

    @Query("from ResponseCount a WHERE YEAR(a.fecha)=?1 AND MONTH(mtc.fecha)=?2 AND DAY(mtc.fecha)=?3")
    public Iterable<ResponseCount> findByDateAndPredefinedQuestion2(@Param("categoryName") LocalDateTime date);


    select p from Persons p where (cast(:createdAt as timestamp) is null or p.createdAt > :createdAt)

    @Transactional
    @Modifying
    @Query("DELETE FROM MyClass mtc WHERE YEAR(mtc.fecha)=?1 AND MONTH(mtc.fecha)=?2 AND DAY(mtc.fecha)=?3")
    public void deleteByFecha(Integer year, Integer month, Integer day);*/

    @Query(value="SELECT rc.* FROM response_count rc WHERE rc.predefined_response_id=:id  ORDER BY id DESC LIMIT 1", nativeQuery = true)
    ResponseCount findLastOneByIdOfPredefinedQuestion(@Param("id") Long id);

}
