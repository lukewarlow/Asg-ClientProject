package com.nsa.team10.asgproject.repositories;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.repositories.daos.DroneDao;
import com.nsa.team10.asgproject.repositories.interfaces.IDroneRepository;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureJdbc
@DirtiesContext
public class DroneRepositoryTests
{
    @Autowired
    private IDroneRepository droneRepository;

    @Test
    public void testCreateDrone() throws ConflictException
    {
        var drone = new DroneDao("Test", "Drone");
        droneRepository.create(drone);

        var result = droneRepository.search("Test Drone");
        Assert.assertEquals(1, result.size());

        var resultDrone = result.get(0);
        Assert.assertEquals("Test", resultDrone.getManufacturer());
        Assert.assertEquals("Drone", resultDrone.getModel());
    }

    @Test(expected = ConflictException.class)
    public void testCreateDroneThrowsExceptionWhenDuplicate() throws ConflictException
    {
        var drone = new DroneDao("Test", "Drone");
        droneRepository.create(drone);
    }

    @Test
    public void testSearch()
    {
        var result = droneRepository.search("Parrot");
        Assert.assertEquals(4, result.size());
        var result2 = droneRepository.search("XYZ123ABC");
        Assert.assertEquals(0, result2.size());
    }

    @Test
    public void testFindAll()
    {
        var filteredPageRequest = new FilteredPageRequest(1, (byte) 10, "id", true, "");
        var result = droneRepository.findAll(filteredPageRequest);
        Assert.assertEquals(3, result.getNoOfPages());
        Assert.assertEquals(10, result.getList().size());
        Assert.assertEquals("DJI", result.getList().get(0).getManufacturer());
        Assert.assertEquals("Mavic 2", result.getList().get(0).getModel());
    }

    @Test
    public void testFindAllWithOrderBy()
    {
        var filteredPageRequest = new FilteredPageRequest(1, (byte) 10, "model", true, "");
        var result = droneRepository.findAll(filteredPageRequest);
        Assert.assertEquals(3, result.getNoOfPages());
        Assert.assertEquals(10, result.getList().size());
        Assert.assertEquals("Parrot", result.getList().get(0).getManufacturer());
        Assert.assertEquals("Anafi", result.getList().get(0).getModel());
        Assert.assertEquals("DJI", result.getList().get(3).getManufacturer());
        Assert.assertEquals("Inspire 1", result.getList().get(3).getModel());
    }
}

