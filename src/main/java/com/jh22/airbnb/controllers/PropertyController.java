package com.jh22.airbnb.controllers;

import com.jh22.airbnb.models.Property;
import com.jh22.airbnb.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

//    Find All Properties
//    http://localhost:2019/properties/properties
    @GetMapping(value = "/properties", produces = "application/json")
    public ResponseEntity<?> listAllProperties()
    {
        List<Property> myProperties = propertyService.findAll();
        return new ResponseEntity<>(myProperties, HttpStatus.OK);
    }

//    Find Property By Id
    @GetMapping(value = "/property/{propertyid}", produces = "application/json")
    public ResponseEntity<?> getPropertyById(@PathVariable long propertyid)
    {
        Property property = propertyService.findPropertyById(propertyid);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

//    Find Property By Special Character
//    TODO make get by special character

//    Find Property By Title
    @GetMapping(value = "/property/{title}", produces = "application/json")
    public ResponseEntity<?> getPropertyByUsername(@PathVariable String title)
    {
        Property property = propertyService.findPropertyByTitle();
        return new ResponseEntity<>(property, HttpStatus.OK);
    }
//    Add New Property
    @PostMapping(value = "/property", consumes = "application/json")
    public ResponseEntity<?> addNewProperty(@Valid @RequestBody Property newproperty) throws URISyntaxException
    {
        newproperty.setPropertyid(0);
        newproperty = propertyService.save(newproperty);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPropertyURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{propertyid}")
                .buildAndExpand(newproperty.getPropertyid())
                .toUri();
        responseHeaders.setLocation(newPropertyURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

//    Update Property By Id
    @PatchMapping(value = "/property/{propertyid}", consumes = "application/json")
    public ResponseEntity<?> updatePropertyById(@RequestBody Property updateProperty, @PathVariable long propertyid)
    {
        propertyService.update(updateProperty, propertyid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    Delete Property
    @DeleteMapping(value = "/property/{propertyid}")
    public ResponseEntity<?> deleteProperty(@PathVariable long propertyid)
    {
        propertyService.delete(propertyid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
