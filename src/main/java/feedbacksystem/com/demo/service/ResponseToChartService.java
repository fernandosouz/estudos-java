package feedbacksystem.com.demo.service;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.model.responses.chart.WrappedPredefinedResponseInterface;
import feedbacksystem.com.demo.model.responses.chart.WrapperQuestion;
import feedbacksystem.com.demo.repository.QuestionRepository;
import feedbacksystem.com.demo.repository.ResponseCountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResponseToChartService {

    private QuestionRepository questionRepository;
    private ResponseCountRepository responseCountRepository;

    public ResponseToChartService(QuestionRepository questionRepository, ResponseCountRepository responseCountRepository) {
        this.questionRepository = questionRepository;
        this.responseCountRepository = responseCountRepository;
    }

    public List<WrapperQuestion> getQuestionWrappedWithCountOfPredefinedResponseSumBetweenDates(List<Long> idsQuestions, String startDate, String endDate) throws ParseException {
        List<Question> questionList = new ArrayList<>();
        questionRepository.findAllByType(idsQuestions).forEach(questionList::add);

        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        /*TODO ver outra forma de adicionar um à data final*/
        Date end = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime() + (1000 * 60 * 60 * 24));

        return questionList.stream().map(question -> {
            WrapperQuestion wrapperQuestion = new WrapperQuestion();
            wrapperQuestion.setQuestionDescription(question.getDescription());
            wrapperQuestion.setQuestionId(question.getId());
            List<WrappedPredefinedResponseInterface> wrappedPredefinedResponseInterface = responseCountRepository.getSumBetweenDatesByQuestion(question.getId(), start, end);
            wrapperQuestion.setWrappedPredefinedResponse(wrappedPredefinedResponseInterface);
            return wrapperQuestion;
        }).collect(Collectors.toList());
    }

}
