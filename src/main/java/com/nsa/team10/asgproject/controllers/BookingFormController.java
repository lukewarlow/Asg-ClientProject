package com.nsa.team10.asgproject.controllers;

import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import com.nsa.team10.asgproject.services.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BookingFormController
{
    private final IBookingService bookingService;

    @Autowired
    public BookingFormController(IBookingService bookingService)
    {
        this.bookingService = bookingService;
    }

    @GetMapping("/bookingForm")
    public String submitForm()
    {
        return "bookingForm.html";
    }

    @PostMapping("/bookingForm")
    public String submitBooking(@Valid NewBookingDto newBooking)
    {
        bookingService.bookingEntry(newBooking);
        return "redirect:/";
    }
}

