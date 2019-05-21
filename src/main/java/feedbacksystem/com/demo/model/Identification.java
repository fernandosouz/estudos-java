package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Identification extends AbstractModel {

    private String name;
}
