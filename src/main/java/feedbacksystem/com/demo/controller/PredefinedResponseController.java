package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.dao.ReponseCountDAO;
import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.repository.PredefinedResponseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.websocket.server.PathParam;

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

    //TODO Otimizar a query removendo o for, e fazendo update de count+1 diretamente na query
    @GetMapping(value = "/predefined-response-add-one/{id}")
    public ResponseEntity add(@PathVariable("id") Long id) {
        reponseCountDAO.addOneCountToPredefinedResponse(id);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
