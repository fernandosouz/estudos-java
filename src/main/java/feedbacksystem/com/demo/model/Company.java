package feedbacksystem.com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import feedbacksystem.com.demo.model.utils.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company extends AbstractEntity {

    private String name;

    @LazyGroup()
    @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private List<Unity> unityList;
}
