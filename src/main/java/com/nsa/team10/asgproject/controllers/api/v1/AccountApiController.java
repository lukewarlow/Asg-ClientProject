package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
    public void register(@Valid NewUserDto newUser)
    {
        accountService.register(newUser);
    }
}
