package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.Unity;
import feedbacksystem.com.demo.repository.UnityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("unity")
public class UnityController {

    UnityRepository unityRepository;

    public UnityController(UnityRepository unityRepository) {
        this.unityRepository = unityRepository;
    }

    @PostMapping(value = "/unity")
    public ResponseEntity add(@RequestBody Unity unity) {
        unityRepository.save(unity);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/unity")
    public ResponseEntity getAll() {
        return new ResponseEntity(unityRepository.findAll(), HttpStatus.CREATED);
    }


    @GetMapping(value = "/unities-by-company/{companyId}")
    public ResponseEntity getAllUnitsByCompanyId(@PathVariable Long companyId) {
        return new ResponseEntity(unityRepository.findAllUnitsByCompanyId(companyId), HttpStatus.CREATED);
    }
}
