package com.trilogyed.priceCalculation.controller;

import com.trilogyed.priceCalculation.model.Product;
import com.trilogyed.priceCalculation.util.feign.ProductApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Product-Controller is a REST Controller, that performs CRUD Operation from the Product-Repository API.
 * */

@RestController
public class ProductsController {

    @Autowired
    ProductApi service;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return service.createProduct(product);
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProductByProductId( @PathVariable String product_id){
        return service.getProductByProductId(product_id);
    }

    @RequestMapping(value = "/product/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProduct(){
        return service.getAllProduct();
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody Product product, @PathVariable String product_id){
        service.updateProduct(product,product_id);
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable String product_id){
        service.deleteProduct(product_id);
    }
}
