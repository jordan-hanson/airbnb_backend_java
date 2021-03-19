package com.jh22.airbnb.services;

import com.jh22.airbnb.exceptions.ResourceNotFoundException;
import com.jh22.airbnb.models.CardInfo;
import com.jh22.airbnb.models.User;
import com.jh22.airbnb.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "cardService")
public class CardServiceImpl implements CardService{
    @Autowired
    private CardRepository cardrepos;

    @Autowired
    private UserService userService;
    @Override
    public List<CardInfo> findAll() {
        List<CardInfo> list = new ArrayList<>();
        cardrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }
    @Transactional
    @Override
    public CardInfo save(CardInfo newCard, long userid) {

        User user = userService.findUserById(userid);

        CardInfo saveCard = new CardInfo();
        saveCard.setUser(user);

        if(newCard.getCardid() != 0)
        {
            cardrepos.findById(newCard.getCardid());
            saveCard.setCardid(newCard.getCardid());
        }
        saveCard.setName(newCard.getName());
        saveCard.setNumber(newCard.getNumber());
        saveCard.setType(newCard.getType());

        return cardrepos.save(saveCard);
    }

    @Override
    public CardInfo findCardById(long cardId)
    throws ResourceNotFoundException
    {
        return cardrepos.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card Id" + cardId + "Not Found!"));
    }

    @Override
    public CardInfo update(CardInfo updateCard, long userid)
    {
        CardInfo currentCard = findCardById(updateCard.getCardid());
        User user = userService.findUserById(userid);
        currentCard.setUser(user);

        if(updateCard.getName() != null)
        {
            currentCard.setName(updateCard.getName());
        }
        if(updateCard.getNumber() != null)
        {
            currentCard.setNumber(updateCard.getNumber());
        }
        if(updateCard.getType() != null)
        {
            currentCard.setType(updateCard.getType());
        }
        if(updateCard.hasvalueforexpdate)
        {
            currentCard.setExpdate(updateCard.getExpdate());
        }
        if(updateCard.hasvalueforsecuritycode)
        {
            currentCard.setSecuritycode(updateCard.getSecuritycode());
        }
        return cardrepos.save(currentCard);
    }

    @Override
    public void delete(long cardId)
    {
        cardrepos.findById(cardId);
        cardrepos.deleteById(cardId);
    }
}
