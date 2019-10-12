package feedbacksystem.com.demo.controller;

import feedbacksystem.com.demo.GetDatasToChartRequest;
import feedbacksystem.com.demo.model.Unity;
import feedbacksystem.com.demo.model.responses.chart.DataToChartOptimized;
import feedbacksystem.com.demo.repository.UnityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @PostMapping(value = "/nps")
    public ResponseEntity getByid(@RequestBody GetDatasToChartRequest getDatasToChartRequest) throws ParseException {

        Unity unity = unityRepository.findById(getDatasToChartRequest.getUnityId()).get();

        Date start = new SimpleDateFormat("yyyy-MM-dd").parse(getDatasToChartRequest.getStartDate());
        /*TODO ver outra forma de adicionar um à data final*/
        Date end = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(getDatasToChartRequest.getEndDate()).getTime() + (1000 * 60 * 60 * 24));

        List<DataToChartOptimized> dataToChartOptimizeds = unityRepository.getSumBetweenDatesByQuestion(unity.getIdNpsQuestion(), start, end );

        return new ResponseEntity(dataToChartOptimizeds, HttpStatus.OK);
    }


    @GetMapping(value = "/unities-by-company/{companyId}")
    public ResponseEntity getAllUnitsByCompanyId(@PathVariable Long companyId) {
        return new ResponseEntity(unityRepository.findAllUnitsByCompanyId(companyId), HttpStatus.CREATED);
    }
}
