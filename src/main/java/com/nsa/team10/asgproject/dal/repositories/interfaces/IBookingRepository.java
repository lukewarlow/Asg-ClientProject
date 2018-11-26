package com.nsa.team10.asgproject.dal.repositories.interfaces;

import com.nsa.team10.asgproject.dal.daos.BookingDao;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.daos.UserWithPasswordDao;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.validation.UserConflictException;

import java.util.Optional;

public interface IBookingRepository
{

    void bookingEntry(NewBookingDto newBooking);
}
