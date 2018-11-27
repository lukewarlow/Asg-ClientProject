package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.services.dtos.NewBookingDto;

public interface IBookingRepository
{
    void bookingEntry(long userId, NewBookingDto newBooking);
}
