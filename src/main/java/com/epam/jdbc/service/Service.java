package com.epam.jdbc.service;

public interface Service <T>{

    T findByEmail(String email);
}
