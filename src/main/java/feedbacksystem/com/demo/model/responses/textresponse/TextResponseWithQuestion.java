package feedbacksystem.com.demo.model.responses.textresponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TextResponseWithQuestion {
    Long getquestionId();
    String getquestionDescription();
    String gettextResponseDescription();
    LocalDateTime gettextResponseDate();
}
