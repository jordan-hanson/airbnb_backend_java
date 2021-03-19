package com.jh22.airbnb.services;

import com.jh22.airbnb.models.CardInfo;

import java.util.List;

public interface CardService {

    List<CardInfo> findAll();

    CardInfo save(CardInfo newCard, long userid);

    CardInfo findCardById(long cardId);

    CardInfo update(CardInfo updateCard, long cardId);

    void delete(long cardId);
}
