package feedbacksystem.com.demo.dao;

import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.model.ResponseCount;
import feedbacksystem.com.demo.repository.PredefinedResponseRepository;
import feedbacksystem.com.demo.repository.ResponseCountRepository;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;

@Component
public class ReponseCountDAO {

    ResponseCountRepository responseCountRepository;
    PredefinedResponseRepository predefinedResponseRepository;

    public ReponseCountDAO(ResponseCountRepository responseCountRepository, PredefinedResponseRepository predefinedResponseRepository) {
        this.responseCountRepository = responseCountRepository;
        this.predefinedResponseRepository = predefinedResponseRepository;
    }

    public void addOneCountToPredefinedResponse(Long idPredefinedResponse){
       Optional<ResponseCount> optionalResponseCount = getResponseCountOfCurrentDayByPredefinedResponse(idPredefinedResponse);

        if(optionalResponseCount.isPresent()){
            optionalResponseCount.get().setCount(optionalResponseCount.get().getCount() + 1);
            responseCountRepository.save(optionalResponseCount.get());
        }else{

            Optional<PredefinedResponse> byId = predefinedResponseRepository.findById(idPredefinedResponse);
            ResponseCount responseCount = ResponseCount.builder().count(1L).predefinedResponse(byId.get()).build();
            responseCountRepository.save(responseCount);
        }
    }


    public Optional<ResponseCount> getResponseCountOfCurrentDayByPredefinedResponse(Long predefinedResponseId){
        ResponseCount responseCount = responseCountRepository.findLastOneByIdOfPredefinedQuestion(predefinedResponseId);
        if(Objects.isNull(responseCount))
            return Optional.empty();
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
