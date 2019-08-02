package com.gatest.taxrepository.controller;

import com.gatest.taxrepository.models.Tax;
import com.gatest.taxrepository.repository.TaxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaxController {

    @Autowired
    TaxesRepository repo;

    @RequestMapping(value = "/tax", method = RequestMethod.POST)
    public Tax createTax(@RequestBody Tax tax) {
        return repo.save(tax);
    }

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.GET)
    public Tax getTaxByCategory( @PathVariable String category_id) {
        return repo.findTaxByCategory(category_id).get();
    }

    @RequestMapping(value = "/tax/all", method = RequestMethod.GET)
    public List<Tax> getAllTax(){
        return repo.findAll();
    }

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.PUT)
    public void updateTax(@RequestBody Tax tax, @PathVariable String category_id) {
        repo.save(tax);
    }

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.DELETE)
    public void deleteTax(@PathVariable String category_id) {
        repo.deleteById(category_id);
    }



}
