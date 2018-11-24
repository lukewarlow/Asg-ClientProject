package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import com.nsa.team10.asgproject.validation.UserConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/account")
public class AccountApiController
{
    private final IAccountService accountService;

    @Autowired
    public AccountApiController(IAccountService accountService)
    {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody NewUserDto newUser)
    {
        try
        {
            accountService.register(newUser);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (UserConflictException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
