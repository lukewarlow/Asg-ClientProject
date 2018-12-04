package com.nsa.team10.asgproject.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/candidates")
public class CandidateAdminController
{
    @GetMapping("")
    public String findAll()
    {
        return "/admin/candidates/index";
    }
}
