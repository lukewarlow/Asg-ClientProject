package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.services.dtos.NewGSCourseResultDto;
import com.nsa.team10.asgproject.services.interfaces.IResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/results")
public class ResultsApiController
{
    private final IResultsService resultsService;

    @Autowired
    public ResultsApiController(IResultsService resultsService)
    {
        this.resultsService = resultsService;
    }

    @PostMapping("/gscourse")
    public ResponseEntity submitGSCourseResults(@Valid @RequestBody NewGSCourseResultDto result)
    {
        resultsService.submitGSCourseResults(result);
        return new ResponseEntity(HttpStatus.OK);
    }
}
