package feedbacksystem.com.demo.dao;

import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.model.ResponseCount;
import feedbacksystem.com.demo.repository.PredefinedResponseRepository;
import feedbacksystem.com.demo.repository.ResponseCountRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ReponseCountDAO {

    ResponseCountRepository responseCountRepository;
    PredefinedResponseRepository predefinedResponseRepository;

    public ReponseCountDAO(ResponseCountRepository responseCountRepository, PredefinedResponseRepository predefinedResponseRepository) {
        this.responseCountRepository = responseCountRepository;
        this.predefinedResponseRepository = predefinedResponseRepository;
    }

    public void addOneToPredefinedResponse(Long idPredefinedResponse){
        PredefinedResponse predefinedResponse = predefinedResponseRepository.findById(idPredefinedResponse).get();

        ResponseCount responseCount2 = responseCountRepository.findLastOneByIdOfPredefinedQuestion(1L);

        System.out.println(responseCount2.getCreatedDateTime());

        System.out.println(new Date());

        System.out.println("a");
    }
}
