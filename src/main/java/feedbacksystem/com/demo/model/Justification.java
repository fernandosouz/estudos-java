package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Justification extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id")
    private Identification identification;

    @ManyToOne
    @JoinColumn(name = "predefined_response_id", referencedColumnName = "id")
    private PredefinedResponse predefinedResponse;
}
