package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewBookingDto;

public interface IBookingService
{
    void bookingEntry(NewBookingDto newBooking);
}
