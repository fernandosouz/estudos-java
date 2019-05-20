package feedbacksystem.com.demo.model;

import feedbacksystem.com.demo.model.utils.AbstractModel;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Identification extends AbstractModel {

    private String name;
}
