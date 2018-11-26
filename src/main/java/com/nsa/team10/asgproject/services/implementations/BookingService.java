package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.dal.daos.BookingDao;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.repositories.implementations.BookingRepository;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IBookingRepository;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IUserRepository;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.services.interfaces.IAccountService;
import com.nsa.team10.asgproject.validation.UserConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingRepository
{
    public final IBookingRepository bookingRepository;

    @Autowired
    public BookingService(IBookingRepository  bookingRepository)
    {
        this.bookingRepository = bookingRepository;
    }


    public void bookingEntry(NewBookingDto newBookingDto)
    {
        var newBooking = new NewBookingDto(newBookingDto.getStartDate(), newBookingDto.getEndDate(), newBookingDto.getLocation(), newBookingDto.getCourseType(),
                newBookingDto.getDateBirth(), newBookingDto.getPlaceBirth(), newBookingDto.getAddress1(), newBookingDto.getAddress2(), newBookingDto.getAddress3()
                , newBookingDto.getPostCode(), newBookingDto.getCounty(), newBookingDto.getCountry(), newBookingDto.getCompanyName(), newBookingDto.getCompanyEmail()
                , newBookingDto.getCompanyPhone(), newBookingDto.getFlyExperiance(), newBookingDto.getDroneManufacturer(), newBookingDto.getDroneModel());
        bookingRepository.bookingEntry(newBooking);
    }
}