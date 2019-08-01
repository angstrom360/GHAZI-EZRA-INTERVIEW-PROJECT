package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.model.Tax;
import com.trilogyed.stwitter.model.Product;
import com.trilogyed.stwitter.util.feign.TaxApi;
import com.trilogyed.stwitter.util.feign.ProductApi;
import com.trilogyed.stwitter.viewmodel.PriceCalculationViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StwitterServiceLayerTest {

    ServiceLayer serviceLayer;

    @Mock
    ProductApi clientProduct;
    @Mock
    TaxApi clientTax;

    private void setUpProductServer(){
        clientProduct=mock(ProductApi.class);
        Product product = new Product();
        product.setProductId("1");
        product.setProductDescription("Tvs");
        product.setPricePerUnit(new BigDecimal("123"));
        product.setCategory("Robots");

        Product product2 = new Product();
        product2.setProductDescription("Tvs");
        product2.setPricePerUnit(new BigDecimal("123"));
        product2.setCategory("Robots");


        List<Product> pList = new ArrayList<>();
        pList.add(product);


        when(clientProduct.createProduct(product)).thenReturn(product);
        when(clientProduct.getProductByProductId("1")).thenReturn(product);
/*        when(clientProduct.getAllProduct().thenReturn(pList);*/

    }

    private void setUpTaxServer(){
        clientTax=mock(TaxApi.class);
        Tax tax = new Tax();
        tax.setCategory("1");
        tax.setTaxPercent(11.11);
        tax.setTaxExempt(false);

        Tax tax2 = new Tax();
        tax2.setTaxPercent(11.11);
        tax2.setTaxExempt(false);

        List<Tax> pList = new ArrayList<>();
        pList.add(tax);


/*        when(clientTax.createTax(tax).thenReturn(tax2));*/
        when(clientTax.getTaxByCategory("1")).thenReturn(tax);
    }

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        setUpTaxServer();
        setUpProductServer();
        serviceLayer = new ServiceLayer(clientTax,clientProduct);
    }

    @Test
    public void createGetDeleteTax() {

        PriceCalculationViewModel tax = new PriceCalculationViewModel();
        tax.setProductid("1");
        tax.setDescription("Stringgsdasdfsdfa1");
        tax.setQuantity(1);
        tax.setPricePerUnit(new BigDecimal("11"));
        tax.setTaxPercent(new BigDecimal("11"));
        tax.setTotalTax(new BigDecimal("11"));
        tax.setTotal(new BigDecimal("11"));
        tax = serviceLayer.create(tax);

        PriceCalculationViewModel tax2 = serviceLayer.getPriceCalculation(tax.getProductid());

        assertEquals(tax, tax2);

        serviceLayer.delete(PriceCalculationViewModel);
        tax = serviceLayer.getPriceCalculation(tax.getProductid());
        assertNull(tax);
    }







}
