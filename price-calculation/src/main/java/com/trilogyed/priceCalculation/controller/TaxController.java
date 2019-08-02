package com.trilogyed.priceCalculation.controller;

import com.trilogyed.priceCalculation.model.Tax;
import com.trilogyed.priceCalculation.util.feign.TaxApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * The Tax-Controller is a REST Controller, that performs CRUD Operation from the Tax-Repository API.
 * */


@RestController
public class TaxController {

    @Autowired
    TaxApi service;

    @RequestMapping(value = "/tax", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Tax createTax(@RequestBody Tax tax){
        return service.createTax(tax);
    }

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Tax getTaxByCategory( @PathVariable String category_id){
        return service.getTaxByCategory(category_id);
    }

    @RequestMapping(value = "/tax/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Tax> getAllTax(){
        return service.getAllTax();
    }

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateTax(@RequestBody Tax tax, @PathVariable String category_id){
        service.updateTax(tax,category_id);
    }

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTax(@PathVariable String category_id){
        service.deleteTax(category_id);
    }

}
