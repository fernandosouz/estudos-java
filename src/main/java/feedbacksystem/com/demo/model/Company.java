package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Transactional
public class Company extends AbstractEntity {

    private String name;

    @Transient
    @JsonIgnoreProperties(value = "company")
    private List<Unity> unityList;

}
