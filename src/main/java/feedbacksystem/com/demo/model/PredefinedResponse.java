package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class PredefinedResponse extends AbstractModel {

    private String description;

    @ManyToOne
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
