package com.nsa.team10.asgproject.controllers.api.v1;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.PaginatedList;
import com.nsa.team10.asgproject.dal.daos.DroneDao;
import com.nsa.team10.asgproject.services.dtos.NewDroneDto;
import com.nsa.team10.asgproject.services.interfaces.IDroneService;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/drones")
public class DroneApiController
{
    private final IDroneService droneService;

    @Autowired
    public DroneApiController(IDroneService droneService)
    {
        this.droneService = droneService;
    }

    @PostMapping("")
    public ResponseEntity create(@Valid @RequestBody NewDroneDto newDrone)
    {
        try
        {
            droneService.create(newDrone);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (ConflictException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("")
    public ResponseEntity<PaginatedList<DroneDao>> findAll(@RequestParam(value = "page", required = false, defaultValue = "1") long page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") byte pageSize, @RequestParam(value = "orderBy", required = false, defaultValue = "id") String orderBy, @RequestParam(value = "orderByAscending", required = false, defaultValue = "true") boolean orderByAscending)
    {
        var pageRequest = new FilteredPageRequest(page, pageSize, orderBy, orderByAscending, "");
        var drones = droneService.findAll(pageRequest);
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }

    @GetMapping("autocomplete")
    public ResponseEntity<List<DroneDao>> search(@RequestParam(value = "search", required = false, defaultValue = "") String search)
    {
        var drones = droneService.search(search);
        return new ResponseEntity<>(drones, HttpStatus.OK);
    }
}
