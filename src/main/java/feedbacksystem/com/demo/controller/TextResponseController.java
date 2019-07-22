package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.TextResponse;
import feedbacksystem.com.demo.model.responses.textresponse.QuestionWithTextResponse;
import feedbacksystem.com.demo.model.responses.textresponse.TextResponseWithDate;
import feedbacksystem.com.demo.model.responses.textresponse.TextResponseWithQuestion;
import feedbacksystem.com.demo.repository.QuestionRepository;
import feedbacksystem.com.demo.repository.TextResponseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("text-response")
public class TextResponseController {

    private TextResponseRepository textResponseRepository;
    private QuestionRepository questionRespository;

    public TextResponseController(TextResponseRepository textResponseRepository,
                                    QuestionRepository questionRepository) {
        this.textResponseRepository = textResponseRepository;
        this.questionRespository = questionRepository;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody List<TextResponse> textResponseList) {
        try {
            textResponseRepository.saveAll(textResponseList);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}/{start}/{end}")
    public ResponseEntity getTextResponseFromQuestionsList(@PathVariable("id") Long companyId, @PathVariable("start") String start, @PathVariable("end") String end){
        if(start != null && end != null && companyId != null) {
            try {
                Date startDateConverted = new SimpleDateFormat("yyyy-MM-dd").parse(start);
                /*TODO ver outra forma de adicionar um à data final*/
                Date endDateConverted = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(end).getTime() + (1000 * 60 * 60 * 24));

                List<QuestionWithTextResponse> questionListResponse = new ArrayList<>();

                List<TextResponseWithQuestion> responseWithQuestions = questionRespository.getQuestionToTextResponse(companyId, startDateConverted, endDateConverted);

                responseWithQuestions.forEach(textResponseWithQuestion -> {
                    if(doNotContainInListResponse(questionListResponse, textResponseWithQuestion)){

                        List<TextResponseWithQuestion> listFilteredByQuestionId = responseWithQuestions.stream().filter(item -> item.getquestionId().equals(textResponseWithQuestion.getquestionId())).collect(Collectors.toList());

                        QuestionWithTextResponse questionWithTextResponse = QuestionWithTextResponse
                                .builder()
                                .questionId(textResponseWithQuestion.getquestionId())
                                .description(textResponseWithQuestion.getquestionDescription())
                                .textResponseWithDateList(setListInsideWithAllQuestionId(listFilteredByQuestionId))
                                .build();

                        questionListResponse.add(questionWithTextResponse);
                    }
                });

                return new ResponseEntity(questionListResponse, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }

    private List<TextResponseWithDate> setListInsideWithAllQuestionId(List<TextResponseWithQuestion> responseWithQuestions) {
        return responseWithQuestions.stream()
                                    .map(item -> TextResponseWithDate
                                            .builder()
                                            .answer(item.gettextResponseDescription())
                                            .date(dateToString(item))
                                            .build()
                                    ).collect(Collectors.toList());
    }

    private String dateToString(TextResponseWithQuestion item) {
        return StringUtils.leftPad(Integer.toString(item.gettextResponseDate().getDayOfMonth()), 2, "0") + "-" +
        StringUtils.leftPad(Integer.toString(item.gettextResponseDate().getMonthValue()), 2, "0") + "-" +
        item.gettextResponseDate().getYear();
    }

    private boolean doNotContainInListResponse(List<QuestionWithTextResponse> questionWithTextResponsesWrapped, TextResponseWithQuestion textResponseWithQuestion) {
        return !questionWithTextResponsesWrapped.stream().anyMatch(item -> item.getQuestionId().equals(textResponseWithQuestion.getquestionId()));
    }
}
