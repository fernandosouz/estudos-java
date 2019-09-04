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
public class Question extends AbstractEntity {

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unity_id")
    @JsonIgnoreProperties({"company"})
    private Unity unity;

    /*@OneToMany(cascade = CascadeType.DETACH, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question"})*/
    @Transient
    private List<TextResponse> textResponseList;

    /*@OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"question", "justifications"})*/
    @Transient
    private List<PredefinedResponse> predefinedResponses;

    private Integer questionType;

    private Boolean showOnDashBoard;
    private Boolean showOnFeedBackApp;

}
