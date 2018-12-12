package com.nsa.team10.asgproject.repositories.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.UserDao;
import com.nsa.team10.asgproject.repositories.daos.UserWithPasswordDao;
import com.nsa.team10.asgproject.services.dtos.EditUserDto;
import com.nsa.team10.asgproject.validation.ConflictException;

import java.util.Optional;

public interface IUserRepository
{
    void create(UserDao newUser, String hashedPassword) throws ConflictException;
    PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllInstructors(FilteredPageRequest pageRequest);
    Optional<UserWithPasswordDao> findWithPasswordByEmail(String email);
    Optional<UserDao> findById(long userId);
    Optional<UserDao> findByIdIncDisabled(long userId);
    Optional<UserDao> findByEmail(String email);
    boolean verifyActivationToken(String email, String token);
    boolean disable(long userId);
    boolean enable(long userId);

    boolean edit(long userId, EditUserDto editedUser);

    String generateActivationToken(String email);
    boolean delete(long userId);
}
