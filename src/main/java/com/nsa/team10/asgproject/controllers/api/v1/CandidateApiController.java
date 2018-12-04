package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.dal.repositories.implementations.CandidateRepository;
import com.nsa.team10.asgproject.dal.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;
import com.nsa.team10.asgproject.services.interfaces.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateApiController
{
    private final ICandidateService candidateService;

    @Autowired
    public CandidateApiController(ICandidateService candidateService)
    {
        this.candidateService = candidateService;
    }

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody NewCandidateDto newCandidate)
    {
        candidateService.create(newCandidate);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("sendpayment")
    public ResponseEntity receipt()
    {
        System.out.print("debug testing blah blah blah");
        candidateService.sendReceipt(true);
        return new ResponseEntity(HttpStatus.OK);
    }
}
