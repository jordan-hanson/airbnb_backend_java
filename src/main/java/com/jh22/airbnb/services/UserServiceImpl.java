package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
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
    public User findUserById(long userId) throws
            ResourceNotFoundException
    {
        return userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User Id" + userId + "Not Found"));
    }

    @Override
    public User findUserByUserName(String userName)
    {
        User uu = userRepo.findByUsername(userName.toLowerCase());
        if (uu == null) {
            throw new ResourceNotFoundException("Username" + userName + "Not Found.");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User newuser)
    {

//        ASK FOR HELP ON THIS TO UNDERSTAND THE GETOWNERPROPERTIES ETC..

//        User newUserMade = new User();
//
//        if (newuser.getUserid() != 0)
//        {
//            userRepo.findById(newuser.getUserid())
//                    .orElseThrow(() -> new ResourceNotFoundException("User id" + newuser.getUserid() + "Not Found."));
//            newUserMade.setUserid(newuser.getUserid());
//        }
//
//        newUserMade.setFirstname(newuser.getFirstname());
//        newUserMade.setLastname(newuser.getLastname());
//        newUserMade.setUsername(newuser.getUsername().toLowerCase());
//        newUserMade.setPrimaryemail(newuser.getPrimaryemail());
//
//
//        newUserMade.getOwnerproperties()
//                .clear();
//

        return userRepo.save(newuser);
    }

    @Transactional
    @Override
    public void update(User updateUser, long userId)
    {

    }

    @Transactional
    @Override
    public void delete(long userId)
    {
        userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User id" + userId + "Not Found to Delete."));
        userRepo.deleteById(userId);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userRepo.deleteAll();
    }
}
