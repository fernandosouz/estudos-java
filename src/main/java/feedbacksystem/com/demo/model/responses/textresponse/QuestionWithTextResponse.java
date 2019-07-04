package feedbacksystem.com.demo.model.responses.textresponse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionWithTextResponse {
    Long questionId;
    String description;
    List<TextResponseWithDate> textResponseWithDates;
}
