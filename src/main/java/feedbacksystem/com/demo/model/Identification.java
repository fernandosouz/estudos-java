package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Identification extends AbstractEntity {

    private String name;
}
