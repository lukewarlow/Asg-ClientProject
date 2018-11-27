package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IUserRepository;
import com.nsa.team10.asgproject.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService
{
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest)
    {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest)
    {
        return userRepository.findAllDisabled(pageRequest);
    }
}
