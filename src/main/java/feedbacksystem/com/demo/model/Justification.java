package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Justification extends AbstractModel {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id")
    private Identification identification;

    @ManyToOne
    @JoinColumn(name = "predefined_response_id", referencedColumnName = "id")
    private PredefinedResponse predefinedResponse;
}
