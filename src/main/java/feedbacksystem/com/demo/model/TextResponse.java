package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TextResponse extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id")
    private Identification identification;
}
