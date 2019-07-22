package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.Company;
import feedbacksystem.com.demo.repository.CompanyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CompanyController {

    CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping(value = "/company")
    public ResponseEntity add(@RequestBody Company company) {
        companyRepository.save(company);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/company")
    public ResponseEntity getAll() {
        return new ResponseEntity(companyRepository.findAll(), HttpStatus.CREATED);
    }
}
