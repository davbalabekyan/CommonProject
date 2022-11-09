package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.service.YearsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;

public class AcademicYearsServiceImpl implements YearsService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(AcademicYearsServiceImpl.class);

    @Override
    public int findIDByStartAndEndDays(LocalDate startDate, LocalDate endDate) {
        int id = -1;
        logger.info("Check if academic year is added to the DB by filling start and end dates.");
        String query = "SELECT id FROM public.academic_year WHERE start_date=? and end_date=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, Date.valueOf(startDate));
            preparedStatement.setDate(2, Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Can not execute query. Something went wrong.");
            throw new RuntimeException("Can not execute query. Something went wrong.");
        }
        return id;
    }
}
