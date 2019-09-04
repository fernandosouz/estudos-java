package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCount extends AbstractEntity {

    private Long count;


    @ManyToOne
    @JoinColumn(name = "predefined_response_id")
    @JsonIgnoreProperties(value = {"responseCount"})
    private PredefinedResponse predefinedResponse;


}
