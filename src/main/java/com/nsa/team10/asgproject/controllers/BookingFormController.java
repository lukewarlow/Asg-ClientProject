package com.nsa.team10.asgproject.controllers;

import java.sql.*;

import com.nsa.team10.asgproject.dal.daos.BookingDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IBookingRepository;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.implementations.BookingService;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import com.nsa.team10.asgproject.services.interfaces.IBookingService;
import com.nsa.team10.asgproject.validation.UserConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping
public class BookingFormController {

    public final IAccountService accountService;

    @Autowired
    public BookingFormController(IAccountService accountService)
    {
        this.accountService = accountService;
    }

    @GetMapping(path = "/bookingForm")
    public String submitForm() {
        return "bookingForm.html";
    }

    @PostMapping("bookingForm")
    public ResponseEntity submitBooking(@Valid NewBookingDto newBooking)
    {
        System.out.println(newBooking.getAll());
        submitBooking(newBooking);
            return new ResponseEntity(HttpStatus.OK);
    }
}

