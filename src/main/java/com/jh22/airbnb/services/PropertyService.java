package com.jh22.airbnb.services;


import com.jh22.airbnb.models.Property;

import java.util.List;

public interface PropertyService {
    Property findPropertyById(long propertyid);

    List<Property> findAll();

    Property findPropertyByTitle(String title);

    Property save(Property newproperty);

    Property update(Property updateProperty, long propertyid);

    void delete(long propertyid);

    void deleteAll();
}
