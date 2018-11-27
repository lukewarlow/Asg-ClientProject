package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IBookingRepository;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.services.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService
{
    private final IBookingRepository bookingRepository;
    private final DefaultUserDetails user;

    @Autowired
    public BookingService(IBookingRepository bookingRepository)
    {
        this.bookingRepository = bookingRepository;
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        user = (DefaultUserDetails) authentication.getPrincipal();
    }

    public void bookingEntry(NewBookingDto newBookingDto)
    {
        var userId = user.getUser().getId();
        bookingRepository.bookingEntry(userId, newBookingDto);
    }
}