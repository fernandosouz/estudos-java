package feedbacksystem.com.demo.service;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.model.responses.WrapperPredefinedresponse;
import feedbacksystem.com.demo.model.responses.WrapperQuestion;
import feedbacksystem.com.demo.repository.QuestionRepository;
import feedbacksystem.com.demo.repository.ResponseCountRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResponseToChartService {

    QuestionRepository questionRepository;
    ResponseCountRepository responseCountRepository;

    public ResponseToChartService(QuestionRepository questionRepository, ResponseCountRepository responseCountRepository) {
        this.questionRepository = questionRepository;
        this.responseCountRepository = responseCountRepository;
    }

    public List<WrapperQuestion> responsetoChart(List<Long> idsQuestions, String startDate, String endDate) throws Exception{
        Iterable<Question> questionList = questionRepository.findAllById(idsQuestions);

        //TODO Passar LocalDateTime com zeros no campo horas para eliminar a questão de adicionar um na data final
        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        /*parse no end adicionando um dia - questão do postgress*/
        Date end = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime() + (1000 * 60 * 60 * 24));
        List<WrapperQuestion> wrapperQuestions = new ArrayList<>();

        //TODO fazer com stream()
        questionList.forEach(question -> {

            WrapperQuestion wrapperQuestion = new WrapperQuestion();
            wrapperQuestion.setDescription(question.getDescription());
            wrapperQuestions.add(wrapperQuestion);
            wrapperQuestion.setWrapperPredefinedresponses(new ArrayList<>());

            //TODO Fazer apenas uma consulta trazendo ids das perguntas predefinidas com o seus respectivos sum's - recebendo um hashmap()
            /*  SELECT response_count.predefined_response_id, sum(count)
                FROM response_count where created_date_time BETWEEN '2019-05-22' AND '2019-05-23'
                GROUP BY response_count.predefined_response_id*/
            question.getPredefinedResponses().forEach( predefinedResponse -> {

                Long sumOfCount = responseCountRepository.getSumBetweenDatesByPredefinedResponse(predefinedResponse.getId(), start, end);

                WrapperPredefinedresponse wrapperPredefinedresponse = new WrapperPredefinedresponse();
                wrapperPredefinedresponse.setSum(sumOfCount != null ? sumOfCount : 0);
                wrapperPredefinedresponse.setDescription(predefinedResponse.getDescription());
                wrapperQuestion.getWrapperPredefinedresponses().add(wrapperPredefinedresponse);

            });
        });

        return  wrapperQuestions;
    }

}
