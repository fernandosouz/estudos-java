package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class QuestionController {

    QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostMapping(value = "/question")
    public ResponseEntity add(@RequestBody Question question) {
        questionRepository.save(question);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
