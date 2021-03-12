package com.jh22.airbnb.repositories;

import com.jh22.airbnb.models.PropertyOwners;
import org.springframework.data.repository.CrudRepository;

public interface PropertyOwnersRepository extends CrudRepository<PropertyOwners, Long> {
}
