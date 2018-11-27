package com.nsa.team10.asgproject.controllers.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("/admin")
public class AdminController
{
    @GetMapping("users")
    public String FindAllUsers()
    {
        return "manage-users.html";
    }
}
