package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.DroneDao;
import com.nsa.team10.asgproject.services.dtos.NewDroneDto;
import com.nsa.team10.asgproject.validation.ConflictException;

public interface IDroneService
{
    void create(NewDroneDto newDrone) throws ConflictException;
    PaginatedList<DroneDao> findAll(FilteredPageRequest pageRequest);
}
