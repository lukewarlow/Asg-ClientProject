package com.nsa.team10.asgproject.controllers.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("/admin/drones")
public class DroneAdminController
{
    @GetMapping()
    public String list()
    {
        return "/admin/drones/index";
    }
}
