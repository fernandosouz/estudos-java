package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.model.Company;
import feedbacksystem.com.demo.model.Unity;
import feedbacksystem.com.demo.repository.CompanyRespository;
import feedbacksystem.com.demo.repository.UnityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("company")
@Controller
public class CompanyController {

    CompanyRespository companyRespository;
    UnityRepository unityRepository;

    public CompanyController(CompanyRespository companyRespository, UnityRepository unityRepository) {
        this.companyRespository = companyRespository;
        this.unityRepository = unityRepository;
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity getAll(@PathVariable Long id) {

        Company company = companyRespository.findById(id).get();
        //company.setUnityList(unityRepository.findunitsByCompanyId(id));

        return new ResponseEntity(company, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(path = "/child/{id}")
    public ResponseEntity getAllChild(@PathVariable Long id) {

        Company company = companyRespository.findById(id).get();
        company.setUnityList(unityRepository.findunitsByCompanyId(id));

        Unity unity = new Unity();
        unity.setCompany(company);

        //unityRepository.save(unity);

        return new ResponseEntity(company, HttpStatus.OK);
    }
}
