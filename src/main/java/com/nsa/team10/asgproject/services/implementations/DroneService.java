package com.nsa.team10.asgproject.services.implementations;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.DroneDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IDroneRepository;
import com.nsa.team10.asgproject.services.dtos.NewDroneDto;
import com.nsa.team10.asgproject.services.interfaces.IDroneService;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneService implements IDroneService
{
    private final IDroneRepository droneRepository;

    @Autowired
    public DroneService(IDroneRepository droneRepository)
    {
        this.droneRepository = droneRepository;
    }

    @Override
    public void create(NewDroneDto newDrone) throws ConflictException
    {
        droneRepository.create(new DroneDao(newDrone.getManufacturer(), newDrone.getModel()));
    }

    @Override
    public PaginatedList<DroneDao> findAll(FilteredPageRequest pageRequest)
    {
        return droneRepository.findAll(pageRequest);
    }
}
