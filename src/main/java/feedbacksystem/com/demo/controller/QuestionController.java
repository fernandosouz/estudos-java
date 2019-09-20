package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.PredefinedResponse;
import feedbacksystem.com.demo.model.Question;
import feedbacksystem.com.demo.repository.PredefinedResponseRepository;
import feedbacksystem.com.demo.repository.QuestionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
@CrossOrigin(origins = "*")
@RequestMapping("question")
public class QuestionController {

    QuestionRepository questionRepository;
    PredefinedResponseRepository predefinedResponseRepository;

    public QuestionController(QuestionRepository questionRepository, PredefinedResponseRepository predefinedResponseRepository) {
        this.questionRepository = questionRepository;
        this.predefinedResponseRepository = predefinedResponseRepository;
    }

    @PostMapping("/to-many-unities")
    public ResponseEntity addToManyUnities(@RequestBody List<Question> questionList) {
        questionRepository.saveAll(questionList);
        questionList.forEach(question -> {
            question.getPredefinedResponses().forEach(predefinedResponse -> predefinedResponse.setQuestion(question));

            predefinedResponseRepository.saveAll(question.getPredefinedResponses());
        });
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity add(@RequestBody Question question) {

        if(question.getDescription().isEmpty() || question.getDescription().isEmpty()) throw new RuntimeException();
        if(question.getQuestionType() == 1 && question.getPredefinedResponses().stream().filter(predefinedResponse -> predefinedResponse.getDescription().equals("")).count() > 0 ) throw new RuntimeException();
        if(question.getQuestionType() == 2)
            question.setPredefinedResponses(new ArrayList<>());

        questionRepository.save(question);

        question.getPredefinedResponses().forEach(predefinedResponse -> predefinedResponse.setQuestion(question));
        predefinedResponseRepository.saveAll(question.getPredefinedResponses());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return new ResponseEntity(questionRepository.findById(id), HttpStatus.OK);
    }

    //TODO Adicionar um filtro para empresas (4)
    @GetMapping("/unity/{unityId}")
    public ResponseEntity get(@PathVariable Long unityId){

        List<Question> questionList = questionRepository.findAllByUnityId(unityId);
        questionList.forEach(question -> {
            question.setPredefinedResponses(predefinedResponseRepository.findAllByQuestionId(question.getId()));
        });


        return new ResponseEntity(questionList, HttpStatus.OK);
    }

    @GetMapping("/unity/app/{unityId}")
    public ResponseEntity getToApp(@PathVariable Long unityId){
        List<Question> allByUnityIdToApp = questionRepository.findAllByUnityIdToApp(unityId);
        if(allByUnityIdToApp.size() > 0 && allByUnityIdToApp != null){
            allByUnityIdToApp.forEach( question -> {
                List<PredefinedResponse> predefinedResponses = predefinedResponseRepository.findAllByQuestionId(question.getId());
                if(predefinedResponses.size() > 0 && question.getQuestionType() == 1){
                    for (PredefinedResponse predefinedRespons : predefinedResponses) {
                        predefinedRespons.setQuestion(null);
                    }
                    question.setPredefinedResponses(predefinedResponses);
                }
            });
        }
        return new ResponseEntity(allByUnityIdToApp, HttpStatus.OK);
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
