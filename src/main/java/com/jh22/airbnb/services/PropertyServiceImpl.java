package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.Property;
import com.jh22.airbnb.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "propertyService")
public class PropertyServiceImpl implements PropertyService{
    @Autowired
    private PropertyRepository propertyrepos;

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
    @Override
    public Property save(Property newproperty) {
        return null;
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
