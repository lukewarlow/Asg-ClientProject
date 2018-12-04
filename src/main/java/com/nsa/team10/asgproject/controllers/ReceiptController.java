package com.nsa.team10.asgproject.controllers;

import com.nsa.team10.asgproject.services.implementations.AccountService;
import com.nsa.team10.asgproject.services.implementations.ReceiptService;
import com.nsa.team10.asgproject.services.interfaces.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("receipt")
public class ReceiptController
{
    private final IReceiptService receiptService;

    @Autowired
    public ReceiptController(IReceiptService receiptService)
    {
        this.receiptService = receiptService;
    }

    @GetMapping("payment")
    public String receipt()
    {
        receiptService.receipt("admin@example.com");
        return "redirect:";    }
}
