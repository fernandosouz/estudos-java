package feedbacksystem.com.demo.model.responses.textresponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TextResponseWithDate {

    String answer;
    String date;

}
