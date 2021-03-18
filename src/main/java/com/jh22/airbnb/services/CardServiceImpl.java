package com.jh22.airbnb.services;

import com.jh22.airbnb.models.CardInfo;
import com.jh22.airbnb.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "cardService")
public class CardServiceImpl implements CardService{
    @Autowired
    private CardRepository cardrepos;

    @Override
    public List<CardInfo> findAll() {
        List<CardInfo> list = new ArrayList<>();
        cardrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public CardInfo save(CardInfo newCard) {
        return null;
    }

    @Override
    public CardInfo findCardById(long cardId) {
        return null;
    }

    @Override
    public CardInfo update(CardInfo updateCard, long cardId) {
        return null;
    }

    @Override
    public void delete(long cardId) {

    }
}
