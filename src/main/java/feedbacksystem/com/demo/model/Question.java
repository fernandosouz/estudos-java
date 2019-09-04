package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class Question extends AbstractEntity {

    public Question() {}

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unity_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"questionList"})
    private Unity unity;

    @OneToMany(cascade = CascadeType.DETACH, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question"})
    private List<TextResponse> textResponseList;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question", "justifications"})
    private List<PredefinedResponse> predefinedResponses;

    private Integer questionType;

    private Boolean showOnDashBoard;
    private Boolean showOnFeedBackApp;

    public void merge(){
        this.getPredefinedResponses().forEach(predefinedResponse -> predefinedResponse.setQuestion(this));
    }
}
