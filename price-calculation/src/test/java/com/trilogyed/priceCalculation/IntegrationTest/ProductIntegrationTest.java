package com.trilogyed.priceCalculation.IntegrationTest;

import com.trilogyed.priceCalculation.model.Product;
import com.trilogyed.priceCalculation.util.feign.ProductApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductIntegrationTest {

    @Autowired
    ProductApi clientProduct;

    @Test
    public void createProductTest() {
        Product product = new Product();
        product.setCategory("produce");
        product.setProductDescription("Kale");
        product.setPricePerUnit(1.50);
        product.setProductId("1004");

        Product product1 = clientProduct.getProductByProductId(product.getProductId());
        product1.setProductId(product.getProductId());
        assertEquals(product, product1);

    }
}
