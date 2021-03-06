package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.UserDao;
import com.nsa.team10.asgproject.services.dtos.EditUserDto;

import java.util.Optional;

public interface IUserService
{
    PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllInstructors(FilteredPageRequest pageRequest);
    Optional<UserDao> findById(long userId);
    Optional<UserDao> findByIdIncDisabled(long userId);
    boolean disable(long userId);
    boolean enable(long userId);

    boolean edit(long userId, EditUserDto editUser);

    boolean delete(long userId);
}
