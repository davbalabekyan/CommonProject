package com.epam.jdbc.service;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherService implements Service<Teacher> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    @Override
    public Teacher findByEmail(String email) {
        Teacher teacher = new Teacher();
        try {
            String query = "SELECT public.teacher.id, name, surname, email FROM public.user INNER JOIN public.teacher ON public.user.id=public.teacher.user_id WHERE public.user.email=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher.setId(resultSet.getInt("id"));
                teacher.setName(resultSet.getString("name"));
                teacher.setSurname(resultSet.getString("surname"));
                teacher.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teacher;
    }
}
