package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.config.DefaultUserDetails;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account")
public class AccountApiController
{
    @GetMapping("/loggedin")
    public ResponseEntity<UserDao> getLoggedInUser()
    {
        var userDetails = getCurrentUserDetails();
        if (userDetails.isPresent())
            return new ResponseEntity<>(userDetails.get().getUser(), HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }

    private Optional<DefaultUserDetails> getCurrentUserDetails()
    {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken))
            return Optional.of((DefaultUserDetails) authentication.getPrincipal());
        else return Optional.empty();
    }
}
