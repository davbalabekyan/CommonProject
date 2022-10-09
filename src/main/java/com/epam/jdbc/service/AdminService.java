package com.epam.jdbc.service;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService implements Service<Admin> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    @Override
    public Admin findByEmail(String email) {
        Admin admin = new Admin();
        try {
            String query = "SELECT public.admin.id, username, surname, email FROM public.user INNER JOIN public.admin ON public.user.id=public.admin.user_id WHERE public.user.email=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setName(resultSet.getString("username"));
                admin.setSurname(resultSet.getString("surname"));
                admin.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }
}
