package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.services.dtos.NewUserDto;
import com.nsa.team10.asgproject.validation.ConflictException;

import java.util.Optional;

public interface IUserService
{
    void create(NewUserDto newUser) throws ConflictException;
    PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest);
    Optional<UserDao> findById(long userId);
    Optional<UserDao> findByIdIncDisabled(long userId);
    boolean disable(long userId);
    boolean enable(long userId);
    boolean delete(long userId);
}
