package com.gatest.products.controller;

import com.gatest.products.models.Product;
import com.gatest.products.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductsRepository repo;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return repo.save(product);
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
    public Product getProductByProductId( @PathVariable String product_id) {
        return repo.findProductByProductId(product_id).get();
    }

    @RequestMapping(value = "/product/all", method = RequestMethod.GET)
    public List<Product> getAllProduct(){
        return repo.findAll();
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.PUT)
    public void updateProduct(@RequestBody Product product, @PathVariable String product_id) {
        repo.save(product);
    }

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String product_id) {
        repo.deleteById(product_id);
    }



}
