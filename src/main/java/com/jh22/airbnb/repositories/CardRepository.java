package com.jh22.airbnb.repositories;

import com.jh22.airbnb.models.CardInfo;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<CardInfo, Long> {
}
