package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.dao.ReponseCountDAO;
import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.repository.PredefinedResponseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@CrossOrigin(origins = "*")
public class PredefinedResponseController {

    PredefinedResponseRepository predefinedResponseRepository;
    ReponseCountDAO reponseCountDAO;

    public PredefinedResponseController(PredefinedResponseRepository predefinedResponseRepository, ReponseCountDAO reponseCountDAO) {
        this.predefinedResponseRepository = predefinedResponseRepository;
        this.reponseCountDAO = reponseCountDAO;
    }

    @PostMapping(value = "/predefined-response")
    public ResponseEntity add(@RequestBody PredefinedResponse predefinedResponse) {
        predefinedResponseRepository.save(predefinedResponse);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //TODO Otimizar a query removendo o for, e fazendo update de count+1 diretamente na query
    @PostMapping(value = "/predefined-response-add-one")
    public ResponseEntity add(@RequestBody List<Long> idsPredefinedResponse) {
        idsPredefinedResponse.forEach(id -> reponseCountDAO.addOneCountToPredefinedResponse(id));
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
