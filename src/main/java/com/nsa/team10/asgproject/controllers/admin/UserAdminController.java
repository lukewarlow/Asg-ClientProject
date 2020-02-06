package com.nsa.team10.asgproject.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController
{
    @GetMapping()
    public String findAllUsers()
    {
        return "/admin/users/index";
    }

    @GetMapping("/{id:[0-9]+}")
    public String findById()
    {
        return "/admin/users/manage";
    }
}
