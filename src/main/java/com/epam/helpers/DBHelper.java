package com.epam.helpers;

import com.epam.jdbc.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBHelper {

    private final UserServiceImpl userService = new UserServiceImpl();
    private final Logger logger = LoggerFactory.getLogger(DBHelper.class);


    public boolean isUserAddedInTheDB() {
        return userService.findByEmail(SharedTestData.getLastGeneratedEmail()).getEmail() == null;
    }
    public boolean isAdminPasswordHashed() {
        logger.info("Check password is encrypted");
        return !userService.findAdminPasswordByEmail(SharedTestData.getLastGeneratedEmail())
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public boolean isTeacherPasswordHashed() {
        logger.info("Check password is encrypted");
        return !userService.findTeacherPasswordByEmail(
                        SharedTestData.getLastGeneratedEmail())
                .equals(SharedTestData.getLastGeneratedPassword());
    }

    public boolean isStudentPasswordHashed() {
        logger.info("Check password is encrypted");
        return !userService.findStudentPasswordByEmail(
                        SharedTestData.getLastGeneratedEmail())
                .equals(SharedTestData.getLastGeneratedPassword());
    }
}
