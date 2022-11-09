package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Admin;
import com.epam.jdbc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminServiceImpl implements UserService<Admin> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public Admin findUserByEmail(String email) {
        Admin admin = new Admin();
        String query = "SELECT public.admin.id, password, username, surname, user_id " +
                "FROM public.admin " +
                "INNER JOIN public.user " +
                "ON public.admin.user_id=public.user.id " +
                "WHERE public.user.email=?;";
        logger.info("Find admin by email");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("username"));
                admin.setSurname(resultSet.getString("surname"));
                admin.setPassword(resultSet.getString("password"));
                admin.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return admin;
    }
}
