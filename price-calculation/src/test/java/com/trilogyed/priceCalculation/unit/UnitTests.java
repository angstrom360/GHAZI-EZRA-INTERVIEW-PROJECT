
package com.trilogyed.priceCalculation.unit;


import com.trilogyed.priceCalculation.model.Product;
import com.trilogyed.priceCalculation.model.Tax;
import com.trilogyed.priceCalculation.service.ServiceLayer;
import com.trilogyed.priceCalculation.util.feign.ProductApi;
import com.trilogyed.priceCalculation.util.feign.TaxApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UnitTests {

    ServiceLayer serviceLayer;
    @Mock
    ProductApi clientProduct;
    @Mock
    TaxApi clientTax;


    private void setUpProductServer(){
        clientProduct=mock(ProductApi.class);
        Product product = new Product();
        product.setProductId("10");
        product.setProductDescription("Tvs");
        product.setPricePerUnit(1.23);
        product.setCategory("123456");

        Product product2 = new Product();
        product2.setProductDescription("Tvs");
        product2.setPricePerUnit(1.23);
        product2.setCategory("123456");


        List<Product> pList = new ArrayList<>();
        pList.add(product);

        when(clientProduct.createProduct(product2)).thenReturn(product);
        when(clientProduct.getProductByProductId("10")).thenReturn(product);

    }

    private void setUpTaxServer(){
        clientTax=mock(TaxApi.class);
        Tax tax = new Tax();
        tax.setCategory("123456");
        tax.setTaxPercent(11.11);
        tax.setTaxExempt(false);

        Tax tax2 = new Tax();
        tax2.setCategory("123456");
        tax2.setTaxPercent(11.11);
        tax2.setTaxExempt(false);

        List<Tax> pList = new ArrayList<>();
        pList.add(tax);


        when(clientTax.getTaxByCategory(tax.getCategory())).thenReturn(tax);
    }

    @Before
    public void setUp() throws Exception{
        setUpTaxServer();
        setUpProductServer();
        serviceLayer = new ServiceLayer(clientTax,clientProduct);
    }
    @Test
    public void getProductTest() {
        Product product = new Product();
        product.setProductId("10");
        product.setProductDescription("Tvs");
        product.setPricePerUnit(1.23);
        product.setCategory("123456");

        Product product1 = clientProduct.getProductByProductId(product.getProductId());

        assertEquals(product, product1);

    }

    @Test
    public void getTaxTest() {
        Tax tax = new Tax();
        tax.setCategory("123456");
        tax.setTaxPercent(11.11);
        tax.setTaxExempt(false);
        Tax tax1 = clientTax.getTaxByCategory(tax.getCategory());

        assertEquals(tax, tax1);

    }



}

