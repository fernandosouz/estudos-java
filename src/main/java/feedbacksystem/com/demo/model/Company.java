package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Company extends AbstractEntity {

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties(value = {"company"})
    private List<Question> questionList;
}
