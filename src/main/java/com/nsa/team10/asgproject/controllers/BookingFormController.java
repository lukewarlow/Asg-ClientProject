package com.nsa.team10.asgproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingFormController {

    @RequestMapping(path = "/bookingForm", method = RequestMethod.GET)
    public String bookingForm() {
        return "bookingForm.html";
    }
}
