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
}
