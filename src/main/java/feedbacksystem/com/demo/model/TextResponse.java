package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TextResponse extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id")
    private Identification identification;
}
