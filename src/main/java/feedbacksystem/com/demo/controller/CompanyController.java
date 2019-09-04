package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.repository.CompanyRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("company")
@Controller
public class CompanyController {

    CompanyRespository companyRespository;

    public CompanyController(CompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAll(@PathVariable Long id) {
        return new ResponseEntity(companyRespository.findById(id), HttpStatus.OK);
    }
}
