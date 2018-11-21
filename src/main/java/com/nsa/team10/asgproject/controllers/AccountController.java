package com.nsa.team10.asgproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("account")
public class AccountController
{
    @GetMapping("register")
    public String register()
    {
        return "register.html";
    }

    @GetMapping("login")
    public String login()
    {
        return "login.html";
    }
}
