package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.Set;


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
            question.merge();
                questionRepository.save(question);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return new ResponseEntity(questionRepository.findById(id), HttpStatus.OK);
    }

    //TODO Adicionar um filtro para empresas (4)
    @GetMapping("/company/{companyId}")
    public ResponseEntity get(@PathVariable Long companyId){
        return new ResponseEntity(questionRepository.findAllByCompanyId(companyId), HttpStatus.OK);
    }


    @PostMapping("/add_list")
    public ResponseEntity addNewQuestionsList(@RequestBody Set<Question> questionSet) {
        if(questionSet.size() > 0) {
            try {
                questionRepository.saveAll(questionSet);
                return new ResponseEntity(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }

    @PutMapping()
    public ResponseEntity updateQuestion(Question question) {
        if(question != null) {
            try{
                questionRepository.save(question);
                return new ResponseEntity(HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deletQuestion (@PathVariable("questionId") Long questionId) {
        Optional<Question> responseQuestion = questionRepository.findById(questionId);
        try{
            // TODO colocar um campo deleted = false na classe commomn
//        responseQuestion.setDeleted(true);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
