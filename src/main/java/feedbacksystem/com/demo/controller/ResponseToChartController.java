package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.GetDatasToChartRequest;
import feedbacksystem.com.demo.model.responses.chart.WrapperQuestion;
import feedbacksystem.com.demo.service.ResponseToChartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class ResponseToChartController {

    private ResponseToChartService responseToChartService;

    public ResponseToChartController(ResponseToChartService responseToChartService) {
        this.responseToChartService = responseToChartService;
    }

    @PostMapping(value = "/get-data-to-chart")
    public ResponseEntity add(@RequestBody GetDatasToChartRequest getDatasToChartRequest) throws ParseException {
        List<WrapperQuestion> wrapperQuestions = responseToChartService.getQuestionWrappedWithCountOfPredefinedResponseSumBetweenDates(getDatasToChartRequest.getUnityId(), getDatasToChartRequest.getStartDate(), getDatasToChartRequest.getEndDate());
        return  new ResponseEntity<>(wrapperQuestions, HttpStatus.OK);
    }

    @PostMapping(value = "/get-data-to-unique-chart")
    public ResponseEntity add2(@RequestBody GetDatasToChartRequest getDatasToChartRequest) throws ParseException {
        List<WrapperQuestion> wrapperQuestions = responseToChartService.getQuestionWrappedWithCountOfPredefinedResponseSumBetweenDatesByUniqueChart(getDatasToChartRequest.getIdsQuestions(), getDatasToChartRequest.getStartDate(), getDatasToChartRequest.getEndDate());
        return  new ResponseEntity<>(wrapperQuestions, HttpStatus.OK);
    }

}
