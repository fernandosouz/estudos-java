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
public class PredefinedResponse extends AbstractEntity {

    private String description;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id")
    @JsonIgnoreProperties(value = {"predefinedResponses"})
    private Question question;

    /*@OneToMany(cascade = CascadeType.DETACH, fetch= FetchType.LAZY)
    @JoinColumn(name = "predefined_response_id")
    @JsonIgnoreProperties(value = {"predefinedResponse"})*/
    @Transient
    private List<ResponseCount> responseCount;

   /* @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "predefined_response_id")
    private List<Justification> justifications;*/
}
