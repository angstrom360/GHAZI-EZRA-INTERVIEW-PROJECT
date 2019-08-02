package com.trilogyed.priceCalculation.util.feign;


import com.trilogyed.priceCalculation.model.Tax;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "tax-repository")
public interface TaxApi {

    @RequestMapping(value = "/tax", method = RequestMethod.POST)
    Tax createTax(@RequestBody Tax tax);

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.GET)
    Tax getTaxByCategory( @PathVariable String category_id);

    @RequestMapping(value = "/tax/all", method = RequestMethod.GET)
    List<Tax> getAllTax();

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.PUT)
    void updateTax(@RequestBody Tax tax, @PathVariable String category_id);

    @RequestMapping(value = "/tax/{category_id}", method = RequestMethod.DELETE)
    void deleteTax(@PathVariable String category_id);

}
