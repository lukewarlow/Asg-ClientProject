package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.repositories.daos.CandidateDao;
import com.nsa.team10.asgproject.repositories.daos.UserDao;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.implementations.AccountService;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import com.nsa.team10.asgproject.services.interfaces.ICandidateService;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountApiController
{
    private final IAccountService accountService;
    private final ICandidateService candidateService;

    @Autowired
    public AccountApiController(IAccountService accountService, ICandidateService candidateService)
    {
        this.accountService = accountService;
        this.candidateService = candidateService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@Valid @RequestBody NewUserDto newUser)
    {
        try
        {
            accountService.register(newUser);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (ConflictException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/loggedinuser")
    public ResponseEntity<UserDao> getLoggedInUser()
    {
        var userDetails = AccountService.getCurrentUserDetails();

        if (userDetails.isPresent())
        {
            return new ResponseEntity<>(userDetails.get().getUser(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/loggedincandidate")
    public ResponseEntity<CandidateDao> getLoggedInCandidate()
    {
        var userDetails = AccountService.getCurrentUserDetails();

        if (userDetails.isPresent())
        {
            var candidate = candidateService.findByEmail(userDetails.get().getUsername());
            if (candidate.isPresent())
                return new ResponseEntity<>(candidate.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
}
