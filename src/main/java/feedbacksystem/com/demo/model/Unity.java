package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Unity extends AbstractEntity {

    private String name;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "unity_id")
    @JsonIgnoreProperties(value = {"unity"})
    private List<Question> questionList;


    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"unityList"})
    private Company company;
}
