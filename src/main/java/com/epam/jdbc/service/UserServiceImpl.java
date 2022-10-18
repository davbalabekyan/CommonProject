package com.epam.jdbc.service;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User findByEmail(String email) {
        User user = new User();
        String query = "SELECT * " +
                "FROM public.\"user\"" +
                "WHERE email=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public String findTeacherPasswordByEmail(String email) {
        User user = new User();
        String query = "SELECT password " +
                "FROM public.user " +
                "INNER JOIN public.teacher " +
                "ON public.user.id=public.teacher.user_id " +
                "WHERE public.user.email=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user.getPassword();
    }

    @Override
    public String findAdminPasswordByEmail(String email) {
        User user = new User();
        String query = "SELECT password " +
                "FROM public.user " +
                "INNER JOIN public.admin " +
                "ON public.user.id=public.admin.user_id " +
                "WHERE public.user.email=?;";
        logger.info("Find admin by email");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
        }
        return user.getPassword();
    }

    @Override
    public String findStudentPasswordByEmail(String email) {
        User user = new User();
        String query = "SELECT password " +
                "FROM public.user INNER JOIN public.student " +
                "ON public.user.id=public.student.user_id WHERE public.user.email=?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user.getPassword();
    }

}
