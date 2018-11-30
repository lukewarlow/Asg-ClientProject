package com.nsa.team10.asgproject.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("/admin/users")
public class UserAdminController
{
    @GetMapping()
    public String findAllUsers()
    {
        return "users/manage-users.html";
    }

    @GetMapping("/{id:[0-9]+}")
    public String findById()
    {
        return "manage-user.html";
    }
}
