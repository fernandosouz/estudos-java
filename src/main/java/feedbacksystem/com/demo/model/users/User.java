package feedbacksystem.com.demo.model.users;

import feedbacksystem.com.demo.model.utils.AbstractEntity;
import feedbacksystem.com.demo.model.utils.AccessType;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "Users")
@Data
public class User extends AbstractEntity {

    @Column(unique=true)
    private String login;

    private String Password;

    @ManyToOne
    @JoinColumn(name = "access_type_id")
    private AccessType accessType;
}
