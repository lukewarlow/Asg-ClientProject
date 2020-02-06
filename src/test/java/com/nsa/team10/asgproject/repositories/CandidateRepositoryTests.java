package com.nsa.team10.asgproject.repositories;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.repositories.daos.DroneDao;
import com.nsa.team10.asgproject.repositories.interfaces.ICandidateRepository;
import com.nsa.team10.asgproject.repositories.interfaces.IDroneRepository;
import com.nsa.team10.asgproject.services.dtos.NewCandidateDto;
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
public class CandidateRepositoryTests
{
    @Autowired
    private ICandidateRepository candidateRepository;

    @Test
    public void testCreateCandidate()
    {
        var candidate = new NewCandidateDto(1, "2000-01-10", null, "102 Fake Street", null, "Faketown", "CF10 2FS", null, "Fake Ltd", "fake@fake.com", "03069990132", "2 Hours", 1);
        candidateRepository.create(1, candidate);

        var result = candidateRepository.findById(4);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("2000-01-10", result.get().getDateOfBirth());
    }

    @Test
    public void testFindAll()
    {
        var filteredPageRequest = new FilteredPageRequest(1, (byte) 10, "id", true, "");
        var result = candidateRepository.findAll(filteredPageRequest);
        Assert.assertEquals(1, result.getNoOfPages());
        Assert.assertEquals(1, result.getList().get(0).getId());
        Assert.assertEquals("2000-12-01", result.getList().get(0).getDateOfBirth());
    }

    @Test
    public void testFindAllWithOrderByDob()
    {
        var filteredPageRequest = new FilteredPageRequest(1, (byte) 10, "dob", true, "");
        var result = candidateRepository.findAll(filteredPageRequest);
        Assert.assertEquals(1, result.getNoOfPages());
        Assert.assertEquals("1990-01-09", result.getList().get(0).getDateOfBirth());
    }

    @Test
    public void testFindById()
    {
        var result = candidateRepository.findById(1);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(2, result.get().getUser().getId());
        Assert.assertEquals("Dave", result.get().getUser().getForename());
        Assert.assertEquals("daves@example.com", result.get().getUser().getEmail());
        Assert.assertEquals("2000-12-01", result.get().getDateOfBirth());

        var result2 = candidateRepository.findById(99);
        Assert.assertFalse(result2.isPresent());
    }
}

