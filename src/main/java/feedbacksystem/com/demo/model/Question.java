package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude={"company"})
public class Question extends AbstractModel {

    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    //@JsonIgnoreProperties(value = {"questionList"})
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private List<TextResponse> textResponseList;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question"})
    private List<PredefinedResponse> predefinedResponses;

}
