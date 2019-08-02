package com.trilogyed.priceCalculation.util.feign;

import com.trilogyed.priceCalculation.model.Product;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@FeignClient(name = "product-repository")
 public interface ProductApi {

    @RequestMapping(value = "/product", method = RequestMethod.POST)
     Product createProduct(@RequestBody Product product);

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.GET)
     Product getProductByProductId( @PathVariable String product_id);


    @RequestMapping(value = "/product/all", method = RequestMethod.GET)
     List<Product> getAllProduct();

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.PUT)
     void updateProduct(@RequestBody Product product, @PathVariable String product_id);

    @RequestMapping(value = "/product/{product_id}", method = RequestMethod.DELETE)
     void deleteProduct(@PathVariable String product_id);

}
