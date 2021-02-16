package com.jh22.airbnb.services;


import com.jh22.airbnb.models.Property;

public interface PropertyService {
    Property findPropertyById(long propertyid);
}
