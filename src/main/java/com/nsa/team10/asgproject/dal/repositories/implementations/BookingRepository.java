package com.nsa.team10.asgproject.dal.repositories.implementations;

import com.nsa.team10.asgproject.dal.repositories.interfaces.IBookingRepository;
import com.nsa.team10.asgproject.services.dtos.NewBookingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

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
        var addressSql = "INSERT INTO address(line_one, line_two, city, county, postcode) VALUES(?, ?, ?, ?, ?);";

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(
                    addressSql,
                    new String[] {"id"});
            pstmt.setString(1, newBooking.getAddress1());
            pstmt.setString(2, newBooking.getAddress2());
            pstmt.setString(3, newBooking.getCity());
            pstmt.setString(4, newBooking.getCounty());
            pstmt.setString(5, newBooking.getPostcode());
            return pstmt;
        }, holder);
        var addressId = holder.getKey().longValue();

        long companyId = -1;
        if (newBooking.getCompanyName() != null && !newBooking.getCompanyName().isEmpty())
        {
            var companySql = "INSERT INTO company(name, phone_number, email) VALUES(?, ?, ?);";
            jdbcTemplate.update(connection -> {
                PreparedStatement pstmt = connection.prepareStatement(
                        companySql,
                        new String[] {"id"});
                pstmt.setString(1, newBooking.getCompanyName());
                pstmt.setString(2, newBooking.getCompanyPhone());
                pstmt.setString(3, newBooking.getCompanyEmail());
                return pstmt;
            }, holder);
            companyId = holder.getKey().longValue();
        }

        var droneSql = "INSERT INTO drone(manufacturer, model, suas_category) VALUES(?, ?, ?);";

        //TODO add suas_category to form
        jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(
                    droneSql,
                    new String[] {"id"});
            pstmt.setString(1, newBooking.getDroneManufacturer());
            pstmt.setString(2, newBooking.getDroneModel());
            pstmt.setString(3, "TODO");
            return pstmt;
        }, holder);
        var droneId = holder.getKey().longValue();

        var candidateSql = "INSERT INTO candidate(candidate_number, user_id, address_id, company_id, dob, drone_id, has_payed) VALUES(?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.update(candidateSql, newBooking.getCandidateReferenceNumber(), userId, addressId, companyId == -1 ? null : companyId, newBooking.getDateOfBirth(), droneId, false);
    }
}
