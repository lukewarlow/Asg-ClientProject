package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;
import com.nsa.team10.asgproject.services.interfaces.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<PaginatedList<CandidateDao>> findAll(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending, @RequestParam(value = "search", required = false, defaultValue = "") String searchTerm)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, searchTerm);
        var candidates = candidateService.findAll(pageRequest);
        System.out.println(candidates.getList().get(0).isPayed());
        return new ResponseEntity<>(candidates, HttpStatus.OK);
    }

    @PostMapping("sendpayment")
    public ResponseEntity receipt()
    {
        System.out.print("debug testing blah blah blah");
        candidateService.sendReceipt(true);
        return new ResponseEntity(HttpStatus.OK);
    }
}
