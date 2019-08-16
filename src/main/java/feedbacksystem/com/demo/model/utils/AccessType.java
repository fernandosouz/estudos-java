package feedbacksystem.com.demo.model.utils;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class AccessType extends AbstractEntity{

    private Integer type;

    private String description;
}
