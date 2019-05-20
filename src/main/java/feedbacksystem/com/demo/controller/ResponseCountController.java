package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.ResponseCount;
import feedbacksystem.com.demo.repository.ResponseCountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ResponseCountController {

    ResponseCountRepository responseCountRepository;

    public ResponseCountController(ResponseCountRepository responseCountRepository) {
        this.responseCountRepository = responseCountRepository;
    }

    @PostMapping(value = "/response-count")
    public ResponseEntity add(@RequestBody ResponseCount responseCount) {
        responseCountRepository.save(responseCount);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
