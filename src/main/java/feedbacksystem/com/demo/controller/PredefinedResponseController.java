package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.dao.ReponseCountDAO;
import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.repository.PredefinedResponseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
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

    @GetMapping(value = "/predefined-response-add-one")
    public ResponseEntity add() {
        return new ResponseEntity(reponseCountDAO.addOneToPredefinedResponse(1L), HttpStatus.CREATED);
    }
}
