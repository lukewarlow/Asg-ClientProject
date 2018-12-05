package com.nsa.team10.asgproject.controllers;

import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("account")
public class AccountController
{
    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService)
    {
        this.accountService = accountService;
    }

    @GetMapping("register")
    public String register()
    {
        return "register.html";
    }

    //TODO move this to api https://stackoverflow.com/questions/29085295/spring-mvc-restcontroller-and-redirect
    @GetMapping("activate")
    public String activate(@RequestParam String email, @RequestParam String token)
    {
        var successful = accountService.verifyActivationToken(email, token);
        if (successful)
        {
            return "redirect:/account/login";
        }
        else
        {
            return "redirect:/account/failedactivation";
        }
    }

    @GetMapping("failedactivation")
    public String failedActivate()
    {
        return "failed-activation.html";
    }

    @GetMapping("login")
    public String login()
    {
        return "login.html";
    }
}
