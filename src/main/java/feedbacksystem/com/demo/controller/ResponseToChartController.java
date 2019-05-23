package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.getDatasToChartRequest;
import feedbacksystem.com.demo.model.responses.chart.WrapperQuestion;
import feedbacksystem.com.demo.service.ResponseToChartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;

@Controller
public class ResponseToChartController {

    private ResponseToChartService responseToChartService;

    public ResponseToChartController(ResponseToChartService responseToChartService) {
        this.responseToChartService = responseToChartService;
    }

    @PostMapping(value = "/get-data-to-chart")
    public ResponseEntity add(@RequestBody getDatasToChartRequest dates) throws ParseException {
        List<WrapperQuestion> wrapperQuestions = responseToChartService.getQuestionWrappedWithCountOfPredefinedResponseSumBetweenDates(dates.getIdsQuestions(), dates.getStartDate(), dates.getEndDate());
        return  new ResponseEntity<>(wrapperQuestions, HttpStatus.OK);
    }

}
