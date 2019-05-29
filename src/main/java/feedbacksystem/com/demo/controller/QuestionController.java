package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("question")
public class QuestionController {

    QuestionRepository questionRepository;

    public QuestionController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody Question question) {
        questionRepository.save(question);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //TODO Adicionar um filtro para empresas (4)
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        return new ResponseEntity(questionRepository.findAllByCompanyId(id), HttpStatus.OK);
    }

}
