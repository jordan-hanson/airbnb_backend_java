package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.Property;
import com.jh22.airbnb.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

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
}
