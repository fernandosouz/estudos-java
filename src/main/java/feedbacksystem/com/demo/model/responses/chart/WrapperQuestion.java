package feedbacksystem.com.demo.model.responses.chart;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WrapperQuestion {

    private String questionDescription;
    private Long questionId;
    private List<WrappedPredefinedResponse> wrappedPredefinedResponse;

}