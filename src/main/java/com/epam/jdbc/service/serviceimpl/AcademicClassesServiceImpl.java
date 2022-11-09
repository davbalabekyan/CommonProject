package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.service.AcademicClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcademicClassesServiceImpl implements AcademicClassesService {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public int findIDByAcademicClass(String academicClass) {
        logger.info("Find ID of the given academic class.");
        int id = -1;
        String query = "SELECT id FROM public.academic_class WHERE class_number=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, academicClass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Academic class does not exists.");
            throw new RuntimeException(e);
        }
        return id;
    }
}
