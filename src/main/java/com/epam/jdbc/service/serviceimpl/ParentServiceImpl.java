package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Parent;
import com.epam.jdbc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParentServiceImpl implements UserService<Parent> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(ParentServiceImpl.class);

    @Override
    public Parent findUserByEmail(String email) {
        Parent parent = new Parent();
        String query = "SELECT public.parent.id, password, name, surname, user_id " +
                "FROM public.parent " +
                "INNER JOIN public.user " +
                "ON public.parent.user_id=public.user.id " +
                "WHERE public.user.email=?;";
        logger.info("Find parent by email");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                parent.setId(resultSet.getInt("id"));
                parent.setSurname(resultSet.getString("surname"));
                parent.setPassword(resultSet.getString("password"));
                parent.setUserId(resultSet.getInt("user_id"));
                parent.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return parent;
    }
}
