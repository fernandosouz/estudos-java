package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.TextResponse;
import feedbacksystem.com.demo.repository.TextResponseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("text-response")
public class TextResponseController {

    private TextResponseRepository textResponseRepository;

    public TextResponseController(TextResponseRepository textResponseRepository) {
        this.textResponseRepository = textResponseRepository;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody List<TextResponse> textResponseList) {
        try {
            textResponseRepository.saveAll(textResponseList);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
