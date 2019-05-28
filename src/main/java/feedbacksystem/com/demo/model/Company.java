package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Company extends AbstractModel {

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties(value = {"company"})
    private List<Question> questionList;

}
