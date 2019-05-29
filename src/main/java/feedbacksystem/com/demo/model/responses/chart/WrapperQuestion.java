package feedbacksystem.com.demo.model.responses.chart;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WrapperQuestion {

    private String description;
    private List<WrappedPredefinedResponseInterface> wrappedPredefinedResponse;

}