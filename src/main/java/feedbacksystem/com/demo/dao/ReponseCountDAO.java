package feedbacksystem.com.demo.dao;

import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.model.ResponseCount;
import feedbacksystem.com.demo.repository.PredefinedResponseRepository;
import feedbacksystem.com.demo.repository.ResponseCountRepository;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

@Component
public class ReponseCountDAO {

    ResponseCountRepository responseCountRepository;
    PredefinedResponseRepository predefinedResponseRepository;

    public ReponseCountDAO(ResponseCountRepository responseCountRepository, PredefinedResponseRepository predefinedResponseRepository) {
        this.responseCountRepository = responseCountRepository;
        this.predefinedResponseRepository = predefinedResponseRepository;
    }

    public boolean addOneToPredefinedResponse(Long idPredefinedResponse){
       Optional<ResponseCount> optionalResponseCount = getResponseCountOfCurrentDayByPredefinedQuestion(idPredefinedResponse);

        if(optionalResponseCount.isPresent()){
            //add
        }else{
            //not add
        }

        return true;
    }


    public Optional<ResponseCount> getResponseCountOfCurrentDayByPredefinedQuestion(Long predefinedQuestionId){
        ResponseCount responseCount = responseCountRepository.findLastOneByIdOfPredefinedQuestion(1L);
        LocalDateTime rightNow = LocalDateTime.now();

        BiPredicate<LocalDateTime, LocalDateTime> isTheSameDate = (date1, date2) ->
                date2.getDayOfMonth() == date1.getDayOfMonth() &&
                        date2.getDayOfMonth() == date1.getDayOfMonth() &&
                        date2.getYear() == date1.getYear();

        if(isTheSameDate.test(responseCount.getCreatedDateTime(), rightNow)){
            return Optional.of(responseCount);
        }else{
            return Optional.empty();
        }
    }
}
