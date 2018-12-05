package com.nsa.team10.asgproject.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/courses")
public class GSCourseAdminController
{
    @GetMapping()
    public String listGSCourses()
    {
        return "/admin/gscourses/index";
    }

    @GetMapping("/{id:[0-9]+}")
    public String manageGSCourse()
    {
        return "/admin/gscourses/manage";
    }
}
