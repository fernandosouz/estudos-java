package feedbacksystem.com.demo.model.responses.chart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrappedPredefinedResponse implements WrappedPredefinedResponseInterface {
    private Long predefinedId;
    private Long sum;
    private String predefinedResponseDescription;

    @Override
    public Long getpredefinedId() {
        return null;
    }

    @Override
    public Long getsum() {
        return null;
    }

    @Override
    public String getpredefinedResponseDescription() {
        return null;
    }
}
