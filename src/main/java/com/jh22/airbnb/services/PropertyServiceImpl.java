package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.Property;
import com.jh22.airbnb.models.PropertyOwners;
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

//        TODO Ask Jeff or reserach the data types with save
//        Do I need to make a boolean and Jsonignoreproperties for zipcode and price?

//        saveProperty.setZipcode(newproperty.getZipcode());
//        saveProperty.setPrice(newproperty.getPrice());
        saveProperty.setPictures(newproperty.getPictures());

//        Collections
//        saveProperty.getOwner()

        return propertyrepos.save(saveProperty);
    }

    @Override
    public void update(Property updateProperty, long propertyid) {

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
