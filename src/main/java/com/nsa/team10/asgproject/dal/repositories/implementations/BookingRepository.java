package com.nsa.team10.asgproject.dal.repositories.implementations;

import com.nsa.team10.asgproject.dal.repositories.interfaces.IBookingRepository;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository implements IBookingRepository
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookingRepository(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void bookingEntry(long userId, NewBookingDto newBooking)
    {
        //TODO add a transaction
        var addressSql = "INSERT INTO address(line_one, line_two, city, county, postcode) VALUES(?, ?, ?, ?, ?); " +
                "SELECT LAST_INSERT_ID();";

        var addressId = jdbcTemplate.queryForObject(addressSql, new Object[] {newBooking.getAddress1(), newBooking.getAddress2(), newBooking.getCity(), newBooking.getCounty(), newBooking.getPostcode()}, Long.class);
        long companyId = -1;
        if (newBooking.getCompanyName() != null)
        {
            var companySql = "INSERT INTO company(name, phone_number, email) VALUES(?, ?, ?); " +
                    "SELECT LAST_INSERT_ID();";
            companyId = jdbcTemplate.queryForObject(companySql, new Object[]{ newBooking.getCompanyName(), newBooking.getCompanyPhone(), newBooking.getCompanyEmail() }, Long.class);
        }

        var droneSql = "INSERT INTO drone(manufacturer, model, suas_category) VALUES(?, ?, ?); " +
                "SELECT LAST_INSERT_ID();";

        var droneId = jdbcTemplate.queryForObject(droneSql, new Object[] {newBooking.getDroneManufacturer(), newBooking.getDroneModel()}, Long.class);

        var candidateSql = "INSERT INTO candidate(candidate_number, user_id, address_id, company_id, dob, drone_id, has_payed) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(candidateSql, newBooking.getCandidateReferenceNumber(), userId, addressId, companyId == -1 ? null : companyId, newBooking.getDateOfBirth(), droneId, false);

        var courseSql = "INSERT INTO course(location, start_date, end_date, candidate) VALUES(?, ?, ?, ?);";

        jdbcTemplate.update(courseSql, newBooking.getLocation(), newBooking.getStartDate(), newBooking.getEndDate());
    }
}
