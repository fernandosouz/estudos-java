package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
public class ResponseCount extends AbstractModel {

    private Long count;


    @ManyToOne
    @JoinColumn(name = "predefined_response_id")
    @JsonIgnoreProperties(value = {"responseCount"})
    private PredefinedResponse predefinedResponse;

}
