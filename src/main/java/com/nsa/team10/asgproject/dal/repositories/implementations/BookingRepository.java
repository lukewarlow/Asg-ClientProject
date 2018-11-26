package com.nsa.team10.asgproject.dal.repositories.implementations;

import com.nsa.team10.asgproject.dal.daos.UserDao;
import com.nsa.team10.asgproject.dal.daos.BookingDao;
import com.nsa.team10.asgproject.dal.repositories.interfaces.IBookingRepository;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import com.nsa.team10.asgproject.validation.UserConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Repository
public class BookingRepository implements IBookingRepository
{
    private final JdbcTemplate jdbcTemplate;

    private static RowMapper<NewBookingDto> bookingMapper;

    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;

        bookingMapper = (rs, i) -> new NewBookingDto(
                rs.getString("startDate"),
                rs.getString("endDate"),
                rs.getString("location"),
                rs.getString("courseType"),
                rs.getString("dateBirth"),
                rs.getString("placeBirth"),
                rs.getString("address1"),
                rs.getString("address2"),
                rs.getString("address3"),
                rs.getString("postCode"),
                rs.getString("county"),
                rs.getString("country"),
                rs.getString("companyName"),
                rs.getString("companyEmail"),
                rs.getString("companyPhone"),
                rs.getString("flyExperiance"),
                rs.getString("droneManufacturer"),
                rs.getString("droneModel")
        );
    }


    public void bookingEntry(NewBookingDto newBooking)
    {
        var sql = "INSERT INTO user (startDate, endDate, location, courseType, dateBirth, placeBirth, address1, address2, address3, postCode, county, " +
                "country, companyName, companyEmail, comapnyPhone, flyExperiance, droneManufacturer,  droneModel) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            jdbcTemplate.update(sql, newBooking.getStartDate(), newBooking.getEndDate(), newBooking.getCourseType(), newBooking.getDateBirth(),
                    newBooking.getPlaceBirth(), newBooking.getAddress1(), newBooking.getAddress2(), newBooking.getAddress3(), newBooking.getPostCode(),
                    newBooking.getCounty(),newBooking.getCountry(), newBooking.getCompanyName(), newBooking.getCompanyEmail(), newBooking.getCompanyPhone(),
                    newBooking.getFlyExperiance(), newBooking.getDroneManufacturer(), newBooking.getDroneModel());

    }
}
