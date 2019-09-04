package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.model.Unity;
import feedbacksystem.com.demo.model.responses.textresponse.IdNameOfUnity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnityRepository extends CrudRepository<Unity, Long> {

    @Query(value="SELECT u.id, u.name FROM unity u WHERE company_id = :companyId", nativeQuery = true)
    List<IdNameOfUnity> findAllUnitsByCompanyId(@Param("companyId") Long companyId);



}
