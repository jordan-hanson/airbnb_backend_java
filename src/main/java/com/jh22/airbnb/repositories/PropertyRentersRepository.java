package com.jh22.airbnb.repositories;

import com.jh22.airbnb.models.PropertyRenters;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRentersRepository extends CrudRepository<PropertyRenters, Long> {
}
