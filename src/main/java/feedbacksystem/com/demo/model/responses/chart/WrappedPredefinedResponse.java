package feedbacksystem.com.demo.model.responses.chart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WrappedPredefinedResponse {
    private Long predefinedId;
    private Long sum;
    private String predefinedResponseDescription;
}
