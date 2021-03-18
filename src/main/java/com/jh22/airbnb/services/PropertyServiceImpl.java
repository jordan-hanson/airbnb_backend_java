package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.Property;
import com.jh22.airbnb.models.PropertyOwners;
import com.jh22.airbnb.models.PropertyRenters;
import com.jh22.airbnb.models.User;
import com.jh22.airbnb.repositories.PropertyOwnersRepository;
import com.jh22.airbnb.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "propertyService")
public class PropertyServiceImpl implements PropertyService{
    @Autowired
    private PropertyRepository propertyrepos;

    @Autowired
    private PropertyOwnersRepository prownRepo;

    @Autowired
    private UserService userService;

    @Override
    public Property findPropertyById(long propertyid) {
        return propertyrepos.findById(propertyid)
                .orElseThrow(() -> new ResourceNotFoundException("Property id" + propertyid + " not Found."));
    }

    @Override
    public List<Property> findAll() {
        List<Property> plist = new ArrayList<>();

        propertyrepos.findAll()
                .iterator()
                .forEachRemaining(plist::add);
        return plist;
    }

    @Override
    public Property findPropertyByTitle(String title)
    throws  ResourceNotFoundException
    {
        return propertyrepos.findByTitle(title);
//                .orElseThrow(()-> new ResourceNotFoundException("Property Title " + title + " Not Found!");
    }
// TODO CREATE NEW PROPERTY AND UPDATE IT.
    @Transactional
    @Override
    public Property save(Property newproperty) {
        Property saveProperty = new Property();

        if(newproperty.getPropertyid() !=0)
        {
            propertyrepos.findById(newproperty.getPropertyid())
                    .orElseThrow(() -> new EntityNotFoundException("Property " + newproperty.getPropertyid() + "Not Found."));
            saveProperty.setPropertyid(newproperty.getPropertyid());
        }
//        For Primitive Data Types/ Strings
        saveProperty.setTitle(newproperty.getTitle());
        saveProperty.setDescription(newproperty.getDescription());
        saveProperty.setStreet(newproperty.getStreet());
        saveProperty.setCity(newproperty.getCity());
        saveProperty.setState(newproperty.getState());

//        Do I need to make a boolean and Jsonignoreproperties for zipcode and price?
//        Answer: Yes, If I am requiring them to have it in my form. YOu don't have to if it defaults to a Zero.
        saveProperty.setZipcode(newproperty.getZipcode());
        saveProperty.setPrice(newproperty.getPrice());
        saveProperty.setPictures(newproperty.getPictures());

//        Collections
        saveProperty.getOwner().clear();
        for(PropertyOwners po: newproperty.getOwner())
        {
            User newOwner = userService.findUserById(po.getOwner().getUserid());
            PropertyOwners propertyOwner = new PropertyOwners();
            propertyOwner.setProperty(saveProperty);
            propertyOwner.setOwner(newOwner);
            propertyOwner.setSubstdate(po.getSubstdate());
            propertyOwner.setSubexpdate(po.getSubexpdate());
            saveProperty.getOwner().add(propertyOwner);
        }
        saveProperty.getRenters().clear();

        return propertyrepos.save(saveProperty);
    }

    @Override
    @Transactional
    public Property update(Property updateProperty, long propertyid)
    {
        Property currentProperty = propertyrepos.findById(propertyid)
                .orElseThrow(() -> new EntityNotFoundException("Property" + propertyid + "Not Found!"));

//        For Primitive Data Types/ Strings
        if(updateProperty.getTitle() != null)
        {
            currentProperty.setTitle(updateProperty.getTitle());
        }
        if(updateProperty.getDescription() != null)
        {
            currentProperty.setDescription(updateProperty.getDescription());
        }
        if(updateProperty.getStreet() != null)
        {
            currentProperty.setStreet(updateProperty.getStreet());
        }
        if(updateProperty.getCity() != null)
        {
            currentProperty.setCity(updateProperty.getCity());
        }
        if(updateProperty.getState() != null)
        {
            currentProperty.setState(updateProperty.getState());
        }
//        Do I need to make a boolean and Jsonignoreproperties for zipcode and price?
//        Answer: Yes, If I am requiring them to have it in my form. You don't have to if it defaults to a Zero.
        if(updateProperty.hasvalueforzipcode)
        {
            currentProperty.setZipcode(updateProperty.getZipcode());
        }
//        saveProperty.setPrice(newproperty.getPrice());
        if(updateProperty.hasvalueforprice)
        {
            currentProperty.setPrice(updateProperty.getPrice());
        }
        if(updateProperty.getPictures() != null)
        {
            currentProperty.setPictures(updateProperty.getPictures());
        }
//        Collection Owner - One to Many
//        Collections are a complete replace
        if(updateProperty.getOwner().size() > 0)
        {
            currentProperty.getOwner().clear();

        for(PropertyOwners po: updateProperty.getOwner())
        {
            User newOwner = userService.findUserById(po.getOwner().getUserid());
            PropertyOwners propertyOwner = new PropertyOwners();
            propertyOwner.setProperty(currentProperty);
            propertyOwner.setOwner(newOwner);
            propertyOwner.setSubstdate(po.getSubstdate());
            propertyOwner.setSubexpdate(po.getSubexpdate());
            currentProperty.getOwner().add(propertyOwner);
        }
        }
        currentProperty.getRenters().clear();

        return propertyrepos.save(currentProperty);
    }

    @Override
    public void delete(long propertyid) {
        propertyrepos.findById(propertyid)
                .orElseThrow(() -> new ResourceNotFoundException("Property Id" + propertyid + "Not Found."));
        propertyrepos.deleteById(propertyid);
    }

    @Override
    public void deleteAll() {
        propertyrepos.deleteAll();
    }
}
