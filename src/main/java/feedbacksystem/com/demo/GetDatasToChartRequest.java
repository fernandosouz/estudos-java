package feedbacksystem.com.demo;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class    GetDatasToChartRequest {
    private Long unityId;
    private String startDate;
    private String endDate;
}
