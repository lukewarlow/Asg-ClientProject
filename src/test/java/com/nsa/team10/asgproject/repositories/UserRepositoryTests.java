package com.nsa.team10.asgproject.repositories;

import com.nsa.team10.asgproject.FilteredPageRequest;
import com.nsa.team10.asgproject.repositories.daos.DroneDao;
import com.nsa.team10.asgproject.repositories.daos.UserDao;
import com.nsa.team10.asgproject.repositories.interfaces.IDroneRepository;
import com.nsa.team10.asgproject.repositories.interfaces.IUserRepository;
import com.nsa.team10.asgproject.validation.ConflictException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureJdbc
@DirtiesContext
@Transactional
public class UserRepositoryTests
{
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreateUser() throws ConflictException
    {
        var user = new UserDao("Test", "User", "test@example.com", "01914980554", UserDao.Role.Guest);
        userRepository.create(user, passwordEncoder.encode("password"));

        var result = userRepository.findByEmail("test@example.com");
        Assert.assertTrue(result.isPresent());
    }

    @Test(expected = ConflictException.class)
    public void testCreateUserUnique() throws ConflictException
    {
        var user = new UserDao("Test", "User", "test@example.com", "01914980554", UserDao.Role.Guest);
        userRepository.create(user, passwordEncoder.encode("password"));
        userRepository.create(user, passwordEncoder.encode("password"));
    }

    @Test
    public void testFindAll()
    {
        var filteredPageRequest = new FilteredPageRequest(1, (byte) 10, "id", true, "");
        var result = userRepository.findAll(filteredPageRequest);
        Assert.assertEquals(1, result.getNoOfPages());
        Assert.assertEquals(8, result.getList().size());
        Assert.assertEquals("John", result.getList().get(0).getForename());
        Assert.assertEquals("Smith", result.getList().get(0).getSurname());
    }

    @Test
    public void testFindAllDisabled()
    {
        var filteredPageRequest = new FilteredPageRequest(1, (byte) 10, "id", true, "");
        var result = userRepository.findAllDisabled(filteredPageRequest);
        Assert.assertEquals(1, result.getNoOfPages());
        Assert.assertEquals(1, result.getList().size());
        Assert.assertEquals("Sally", result.getList().get(0).getForename());
        Assert.assertEquals("Smith", result.getList().get(0).getSurname());
    }

    @Test
    public void testFindAllInstructors()
    {
        var filteredPageRequest = new FilteredPageRequest(1, (byte) 10, "id", true, "");
        var result = userRepository.findAllInstructors(filteredPageRequest);
        Assert.assertEquals(1, result.getNoOfPages());
        Assert.assertEquals(2, result.getList().size());
        Assert.assertEquals("Jodie", result.getList().get(0).getForename());
        Assert.assertEquals("Bright", result.getList().get(0).getSurname());
        Assert.assertFalse(result.getList().stream().map(UserDao::getForename).collect(Collectors.toList()).contains("Sally"));
    }

    @Test
    public void testFindById()
    {
        var result = userRepository.findById(5);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("Abbey", result.get().getForename());
        Assert.assertEquals("Foster", result.get().getSurname());
        var result2 = userRepository.findById(99);
        Assert.assertFalse(result2.isPresent());
        var result3 = userRepository.findById(6);
        Assert.assertFalse(result3.isPresent());
    }

    @Test
    public void testFindByIdIncDisabled()
    {
        var result = userRepository.findByIdIncDisabled(5);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("Abbey", result.get().getForename());
        Assert.assertEquals("Foster", result.get().getSurname());
        var result2 = userRepository.findByIdIncDisabled(6);
        Assert.assertTrue(result2.isPresent());
        Assert.assertEquals("Sally", result2.get().getForename());
        Assert.assertEquals("Smith", result2.get().getSurname());
        var result3 = userRepository.findByIdIncDisabled(99);
        Assert.assertFalse(result3.isPresent());
    }

    @Test
    public void testEnable()
    {
        var result = userRepository.findById(6);
        Assert.assertFalse(result.isPresent());
        userRepository.enable(6);
        var result2 = userRepository.findById(6);
        Assert.assertTrue(result2.isPresent());
    }

    @Test
    public void testDisable()
    {
        var result = userRepository.findById(1);
        Assert.assertTrue(result.isPresent());
        userRepository.disable(1);
        var result2 = userRepository.findById(1);
        Assert.assertFalse(result2.isPresent());
    }

    @Test
    public void testDelete()
    {
        var result = userRepository.findByIdIncDisabled(6);
        Assert.assertTrue(result.isPresent());
        userRepository.delete(6);
        var result2 = userRepository.findByIdIncDisabled(6);
        Assert.assertFalse(result2.isPresent());
    }
}

