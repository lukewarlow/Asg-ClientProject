package com.nsa.team10.asgproject.repositories;

import com.nsa.team10.asgproject.repositories.interfaces.IGSCourseRepository;
import com.nsa.team10.asgproject.services.dtos.NewGSCourseDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureJdbc
@DirtiesContext
@Transactional
public class GSCourseRepositoryTests
{
    @Autowired
    private IGSCourseRepository gsCourseRepository;

    @Test
    public void testCreateDrone()
    {
        var result = gsCourseRepository.findById(3);
        Assert.assertFalse(result.isPresent());
        var course = new NewGSCourseDto("2010-07-12", "2010-07-15", 1, 1);
        gsCourseRepository.create(course);

        var result2 = gsCourseRepository.findById(3);
        Assert.assertTrue(result2.isPresent());
    }
}

