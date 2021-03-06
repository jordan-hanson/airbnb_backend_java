package com.jh22.airbnb.repositories;

import com.jh22.airbnb.models.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long> {
    Property findByTitle(String title);
}
