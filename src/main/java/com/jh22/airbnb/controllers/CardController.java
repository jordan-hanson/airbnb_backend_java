package com.jh22.airbnb.controllers;


import com.jh22.airbnb.models.CardInfo;
import com.jh22.airbnb.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Id;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

//    Find All Users
//    http://localhost:2019/cards/cards
    @GetMapping(value= "/cards", produces = "application/json")
    public ResponseEntity<?> listAllCards()
    {
        List<CardInfo> allCards = cardService.findAll();
        return new ResponseEntity<>(allCards, HttpStatus.OK);
    }
//    Find Card By Id
//    http://localhost:2019/cards/1
    @GetMapping(value = "/card/{cardId}", produces = "application/json")
    public ResponseEntity<?> findCardById(@PathVariable long cardId)
    {
        CardInfo card = cardService.findCardById(cardId);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }
//    Save New Card
//    http://localhost:2019/cards/card
    @PostMapping(value = "/card/user/{userid}", consumes = "application/json")
    public ResponseEntity<?> addNewCard(@Valid @RequestBody CardInfo newCard, @PathVariable long userid)
    {
        newCard.setCardid(0);
        newCard = cardService.save(newCard, userid);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCardURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cardid}")
                .buildAndExpand(newCard.getCardid())
                .toUri();
        responseHeaders.setLocation(newCardURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }
//    Update Card Info By Id
//    http://localhost:2019/cards/card/1
    @PatchMapping(value = "/card/{cardId}", consumes = "application/json", produces = "application/json")
    public  ResponseEntity<?> updateCardById(@RequestBody CardInfo updateCard, @PathVariable long cardId)
    {
        cardService.update(updateCard, cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//   Delete Card
//   http://localhost:2019/cards/card/1
    @DeleteMapping(value = "/card/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable long cardId)
    {
        cardService.delete(cardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
