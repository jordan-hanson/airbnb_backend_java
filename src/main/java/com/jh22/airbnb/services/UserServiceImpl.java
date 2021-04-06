package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.*;
import com.jh22.airbnb.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    @Autowired
    private PropertyOwnersRepository prownRepo;

    @Autowired
    private PropertyRentersRepository renterRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRolesRepository userRoleRepo;
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

        User newUserMade = new User();

        if(newuser.getUserid() != 0)
        {
            userRepo.findById(newuser.getUserid());
            newUserMade.setUserid(newuser.getUserid());
        }
        newUserMade.setFirstname(newuser.getFirstname());
        newUserMade.setLastname(newuser.getLastname());
        newUserMade.setUsername(newuser.getUsername().toLowerCase());
        newUserMade.setPrimaryemail(newuser.getPrimaryemail());

//      PASSWORD OWNERPROPERTIES RENTALPROPERTIES AND ROLES
        newUserMade.setPasswordNoEncrypt(newuser.getPassword());
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
        User existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User" + userId + "Not Found!"));

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
            existingUser.setPasswordNoEncrypt(updateUser.getPassword());
        }

        if (updateUser.getOwnerproperties()
                        .size() > 0)
        {
            existingUser.getOwnerproperties()
                        .clear();
            for (PropertyOwners po : updateUser.getOwnerproperties())
            {
                PropertyOwners newOwner = prownRepo.findById(po.getOwner().getUserid())
                        .orElseThrow(() -> new EntityNotFoundException("Property Owner" + po.getOwner().getUserid() + "Not found!"));
                existingUser.getOwnerproperties().add(newOwner);
            }
        }
        if (updateUser.getRentproperties()
                        .size() > 0)
        {
            existingUser.getRentproperties()
                        .clear();
            for (PropertyRenters pr : updateUser.getRentproperties())
            {
                PropertyRenters newRenter = renterRepo.findById(pr.getRenter().getUserid())
                        .orElseThrow(() -> new EntityNotFoundException("Property Renter" + pr.getRenter().getUserid() + "Not Found."));
                existingUser.getRentproperties().add(newRenter);
            }
        }
        if (updateUser.getRoles()
                    .size() > 0)
        {
            existingUser.getRoles()
                        .clear();
            for (UserRoles ur: updateUser.getRoles())
            {
                UserRoles addRole = userRoleRepo.findById(ur.getRole().getRoleid())
                        .orElseThrow(() -> new EntityNotFoundException("Role id" + ur.getRole().getRoleid()));

                existingUser.getRoles()
                        .add(addRole);
            }
        }
        return userRepo.save(existingUser);
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
