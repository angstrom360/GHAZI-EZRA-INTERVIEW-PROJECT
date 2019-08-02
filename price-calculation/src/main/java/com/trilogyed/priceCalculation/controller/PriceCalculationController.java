package com.trilogyed.priceCalculation.controller;

import com.trilogyed.priceCalculation.service.ServiceLayer;
import com.trilogyed.priceCalculation.viewmodel.PriceCalculationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@CacheConfig(cacheNames = {"priceCalcuation"})
public class PriceCalculationController {

    @Autowired
    ServiceLayer service;

    public PriceCalculationController(ServiceLayer service) {
        this.service = service;
    }

    @Cacheable
    @RequestMapping(value = "/api/price/product/{productid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PriceCalculationViewModel calcualtePrice(
            @PathVariable String productid,
            @RequestParam(required = true) int quantity,
            @RequestParam(required = false) Boolean taxExempt){
        System.out.println("Getting Calculation Results!!!");

        return service.calculatePrice(productid, quantity, taxExempt);
    }

}
