package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ResponseCount extends AbstractModel {

    private Long count;


    @ManyToOne
    @JoinColumn(name = "predefined_response_id")
    private PredefinedResponse predefinedResponse;

}
