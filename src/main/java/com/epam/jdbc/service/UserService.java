package com.epam.jdbc.service;

import com.epam.jdbc.model.User;

public interface UserService {

    User findByEmail(String email);

    String findTeacherPasswordByEmail(String email);

    String findAdminPasswordByEmail(String email);

    String findStudentPasswordByEmail(String email);
}
