package com.jh22.airbnb.services;

import com.jh22.airbnb.models.User;
import com.jh22.airbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        userRepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long userId) {
        return null;
    }

    @Override
    public User findUserByUserName(String userName) {
        return null;
    }

    @Transactional
    @Override
    public User save(User newuser) {
        return null;
    }

    @Transactional
    @Override
    public void update(User updateUser, long userId) {

    }

    @Transactional
    @Override
    public void delete(long userId) {

    }

    @Transactional
    @Override
    public void deleteAll() {

    }
}
