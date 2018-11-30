package com.nsa.team10.asgproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidate")
public class CandidateController
{
    @GetMapping("/signup")
    public String submitForm()
    {
        return "sign-up.html";
    }
}

