package com.nsa.team10.asgproject.services.interfaces;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.repositories.daos.DroneDao;
import com.nsa.team10.asgproject.services.dtos.NewDroneDto;
import com.nsa.team10.asgproject.validation.ConflictException;

import java.util.List;

public interface IDroneService
{
    void create(NewDroneDto newDrone) throws ConflictException;
    PaginatedList<DroneDao> findAll(FilteredPageRequest pageRequest);
    List<DroneDao> search(String search);
}
