package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.*;
import com.jh22.airbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PropertyService propertyService;

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

//        STEP 1 VERIFY NEW USERNAME (UNIQUE = TRUE) ISN'T ON DB
        User existingUser = userRepo.findByUsername(newuser.getUsername());
        if (existingUser != null )
        {
            throw new ValidationException("User with " + newuser.getUsername() + " has already been taken.");
        }

        User newUserMade = new User();

        newUserMade.setFirstname(newuser.getFirstname());
        newUserMade.setLastname(newuser.getLastname());
        newUserMade.setUsername(newuser.getUsername().toLowerCase());
        newUserMade.setPrimaryemail(newuser.getPrimaryemail());

//      PASSWORD OWNERPROPERTIES RENTALPROPERTIES AND ROLES
        newUserMade.setPassword(newuser.getPassword());
//      OWNERPROPERTIES RELATIONSHIP - CREATE SERVICES FOR PROPERTYID AND USERID IS ABOVE^ AND AUTOWIRE IN
        newUserMade.getOwnerproperties()
                .clear();
        for (PropertyOwners up : newuser.getOwnerproperties())
        {
            Property property = new Property();
            if (up.getProperty().getPropertyid() != 0) {

                property = propertyService.findPropertyById(up.getProperty().getPropertyid());
            } else {
                property.setTitle(up.getProperty().getTitle());
                property.setDescription(up.getProperty().getDescription());
                property.setStreet(up.getProperty().getStreet());
                property.setCity(up.getProperty().getCity());
                property.setState(up.getProperty().getState());
                property.setZipcode(up.getProperty().getZipcode());
                property.setPrice(up.getProperty().getPrice());
                property.setPictures(up.getProperty().getPictures());
            }
            PropertyOwners newPropertyOwner = new PropertyOwners(
                    newUserMade,
                    property,
                    up.getSubstdate(),
                    up.getSubexpdate());
            newUserMade.getOwnerproperties().add(newPropertyOwner);
        }
        newUserMade.getRentproperties()
                .clear();
        for (PropertyRenters pr : newuser.getRentproperties())
        {
            Property property = propertyService.findPropertyById(pr.getProperty().getPropertyid());
            PropertyRenters newPropertyRenters = new PropertyRenters(
                    newUserMade,
                    property);
            newUserMade.getRentproperties().add(newPropertyRenters);
        }
        newUserMade.getRoles()
                .clear();
        for (UserRoles ur : newuser.getRoles())
        {
            Role role = roleService.findRoleById(ur.getRole().getRoleid());
            UserRoles newUserRole = new UserRoles(
                    newUserMade,
                    role
            );
            newUserMade.getRoles().add(newUserRole);
        }

        return userRepo.save(newUserMade);
    }

    @Transactional
    @Override
    public User update(User updateUser, long userId)
    {
//        TODO MODIFY TO UPDATE USER
////        modification in process
//        //        STEP 1 VERIFY NEW USERNAME (UNIQUE = TRUE) ISN'T ON DB
        User existingUser = userRepo.findByUsername(updateUser.getUsername());
        if (existingUser != null )
        {
            throw new ValidationException("User with " + updateUser.getUsername() + " has already been taken.");
        }
        if (updateUser.getFirstname() != null)
        {
            existingUser.setFirstname(updateUser.getFirstname().toLowerCase());
        }
        if (updateUser.getLastname() != null)
        {
            existingUser.setLastname(updateUser.getLastname().toLowerCase());
        }
        if (updateUser.getUsername() != null)
        {
            existingUser.setUsername(updateUser.getUsername().toLowerCase());
        }
        if (updateUser.getPrimaryemail() != null)
        {
            existingUser.setPrimaryemail(updateUser.getPrimaryemail().toLowerCase());
        }
        if (updateUser.getPassword() != null)
        {
            existingUser.setPassword(updateUser.getPassword().toLowerCase());
        }
////      PASSWORD OWNERPROPERTIES RENTALPROPERTIES AND ROLES
//        newUserMade.setPassword(newuser.getPassword());
////      OWNERPROPERTIES RELATIONSHIP - CREATE SERVICES FOR PROPERTYID AND USERID IS ABOVE^ AND AUTOWIRE IN
//        newUserMade.getOwnerproperties()
//                .clear();
//        for (PropertyOwners up : newuser.getOwnerproperties())
//        {
//            Property property = new Property();
//            if (up.getProperty().getPropertyid() != 0) {
//
//                property = propertyService.findPropertyById(up.getProperty().getPropertyid());
//            } else {
//                property.setTitle(up.getProperty().getTitle());
//                property.setDescription(up.getProperty().getDescription());
//                property.setStreet(up.getProperty().getStreet());
//                property.setCity(up.getProperty().getCity());
//                property.setState(up.getProperty().getState());
//                property.setZipcode(up.getProperty().getZipcode());
//                property.setPrice(up.getProperty().getPrice());
//                property.setPictures(up.getProperty().getPictures());
//            }
//            PropertyOwners newPropertyOwner = new PropertyOwners(
//                    newUserMade,
//                    property,
//                    up.getSubstdate(),
//                    up.getSubexpdate());
//            newUserMade.getOwnerproperties().add(newPropertyOwner);
//        }
//        newUserMade.getRentproperties()
//                .clear();
//        for (PropertyRenters pr : newuser.getRentproperties())
//        {
//            Property property = propertyService.findPropertyById(pr.getProperty().getPropertyid());
//            PropertyRenters newPropertyRenters = new PropertyRenters(
//                    newUserMade,
//                    property);
//            newUserMade.getRentproperties().add(newPropertyRenters);
//        }
//        newUserMade.getRoles()
//                .clear();
//        for (UserRoles ur : newuser.getRoles())
//        {
//            Role role = roleService.findRoleById(ur.getRole().getRoleid());
//            UserRoles newUserRole = new UserRoles(
//                    newUserMade,
//                    role
//            );
//            newUserMade.getRoles().add(newUserRole);
//        }
//
//        return userRepo.save(newUserMade);
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
