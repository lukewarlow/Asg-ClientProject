package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.dal.daos.BookingDao;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.validation.UserConflictException;
import org.springframework.context.annotation.Bean;


public interface IBookingService
{
    void bookingEntry(NewBookingDto newBooking);
}
