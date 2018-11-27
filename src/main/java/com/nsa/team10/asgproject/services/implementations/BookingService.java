package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IBookingRepository;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.services.interfaces.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class BookingService implements IBookingService
{
    private final IBookingRepository bookingRepository;

    @Autowired
    public BookingService(IBookingRepository bookingRepository)
    {
        this.bookingRepository = bookingRepository;
    }

    public void bookingEntry(NewBookingDto newBookingDto)
    {
        if (!getCurrentUserDetails().isPresent())
            throw new AccessDeniedException("Need to be logged in");

        var userId = getCurrentUserDetails().get().getUser().getId();
        bookingRepository.bookingEntry(userId, newBookingDto);
    }

    private Optional<DefaultUserDetails> getCurrentUserDetails()
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return Optional.of((DefaultUserDetails) authentication.getPrincipal());
        else return Optional.empty();
    }
}