package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.UserDao;

public interface IUserService
{
    PaginatedList<UserDao> findAll(FilteredPageRequest pageRequest);
    PaginatedList<UserDao> findAllDisabled(FilteredPageRequest pageRequest);
}
