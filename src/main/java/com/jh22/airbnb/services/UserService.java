package com.jh22.airbnb.services;

import com.jh22.airbnb.models.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findUserById(long userId);

    User findUserByUserName(String userName);

    User save(User newuser);

    User update(User updateUser, long userId);

    void delete(long userId);

    void deleteAll();
}
