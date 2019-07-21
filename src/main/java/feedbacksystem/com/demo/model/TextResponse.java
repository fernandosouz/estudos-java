package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TextResponse extends AbstractEntity {

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch= FetchType.LAZY)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"TextResponse"})
    private Question question;

    private String answer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identification_id")
    private Identification identification;
}
