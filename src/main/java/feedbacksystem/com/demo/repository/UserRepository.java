package feedbacksystem.com.demo.repository;

import feedbacksystem.com.demo.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    @Query(value = "SELECT u.* FROM users u WHERE u.login = :login", nativeQuery = true)
    User findByLogin(@Param("login") String login);

}
