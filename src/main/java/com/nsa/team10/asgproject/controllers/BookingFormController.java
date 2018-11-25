package com.nsa.team10.asgproject.controllers;

import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class BookingFormController {

    @GetMapping(path = "/bookingForm")
    public String bookingForm() {
        return "bookingForm.html";
    }

    @RequestMapping(path = "/submitForm")
    public String submitForm() {
     return "bookingForm.html";
    }
}

