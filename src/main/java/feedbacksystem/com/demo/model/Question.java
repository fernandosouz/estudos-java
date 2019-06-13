package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import feedbacksystem.com.demo.utils.enums.QuestionsTypes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Question extends AbstractEntity {

    public Question() {}

    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"questionList"})
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question"})
    private List<TextResponse> textResponseList;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question"})
    private List<PredefinedResponse> predefinedResponses;


    private Integer questionType;


    public QuestionsTypes getQuestionType() {
        return QuestionsTypes.toEnum(questionType);
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }
}
