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

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"predefinedResponses"})
    private Question question;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "predefined_response_id")
    @JsonIgnoreProperties(value = {"predefinedResponse"})
    private List<ResponseCount> responseCount;

    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "predefined_response_id")
    private List<Justification> justifications;
}
