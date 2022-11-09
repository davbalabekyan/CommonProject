package com.epam.jdbc.service.serviceimpl;

import com.epam.jdbc.config.DBConnectionProvider;
import com.epam.jdbc.model.Student;
import com.epam.jdbc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentServiceImpl implements UserService<Student> {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public Student findUserByEmail(String email) {
        Student student = new Student();
        String query = "SELECT public.student.id, name, surname, password, address, gender, " +
                "blood_group, date, academic_class_id, parent_id, user_id " +
                "FROM public.student INNER JOIN public.user " +
                "ON public.student.user_id=public.user.id WHERE public.user.email=?;";
        logger.info("Find student by email");
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSurname(resultSet.getString("surname"));
                student.setPassword(resultSet.getString("password"));
                student.setGender(resultSet.getString("gender"));
                student.setAddress(resultSet.getString("address"));
                student.setBloodGroup(resultSet.getString("blood_group"));
                student.setAcademicClassId(resultSet.getInt("academic_class_id"));
                student.setParentId(resultSet.getInt("parent_id"));
                student.setBirthDay(resultSet.getDate("date"));
                student.setUserId(resultSet.getInt("user_id"));
            }
        } catch (SQLException e) {
            logger.error("Can not execute query");
            throw new RuntimeException("Can not execute query, something went wrong");
        }
        return student;
    }
}
