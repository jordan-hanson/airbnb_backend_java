package com.jh22.airbnb.controllers;


import com.jh22.airbnb.models.CardInfo;
import com.jh22.airbnb.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

//  TODO: AUTHORIZE ONLY ADMIN WHEN SECURITY IS ADDED
    @GetMapping(value= "/cards", produces = "application/json")
    public ResponseEntity<?> listAllCards()
    {
        List<CardInfo> allCards = cardService.findAll();
        return new ResponseEntity<>(allCards, HttpStatus.OK);
    }
}
