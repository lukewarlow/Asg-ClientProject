package com.nsa.team10.asgproject.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AsgErrorController implements ErrorController
{
    @RequestMapping("/error")
    public String handleErrors(HttpServletRequest request)
    {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            var statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.FORBIDDEN.value())
                return "/error/error-403.html";
            else if(statusCode == HttpStatus.NOT_FOUND.value())
                return "/error/error-404.html";
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
                return "/error/error-500.html";
        }
        return "/error/error.html";
    }


    @Override
    public String getErrorPath()
    {
        return "/error";
    }
}
