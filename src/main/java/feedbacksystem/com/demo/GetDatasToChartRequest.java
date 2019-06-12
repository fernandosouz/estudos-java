package feedbacksystem.com.demo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class    GetDatasToChartRequest {
    private List<Long> idsQuestions;
    private String startDate;
    private String endDate;
}
