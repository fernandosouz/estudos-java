package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Question extends AbstractEntity {

    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"questionList"})
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private List<TextResponse> textResponseList;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question"})
    private List<PredefinedResponse> predefinedResponses;

}
