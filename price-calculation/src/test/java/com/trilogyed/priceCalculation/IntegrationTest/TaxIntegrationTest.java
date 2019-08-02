package com.trilogyed.priceCalculation.IntegrationTest;


import com.trilogyed.priceCalculation.model.Tax;
import com.trilogyed.priceCalculation.util.feign.TaxApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaxIntegrationTest {

    @Autowired
    TaxApi clientTax;

    @Test
    public void createTaxTest() {
        Tax tax = new Tax();
        tax.setCategory("supply");
        tax.setTaxExempt(true);
        tax.setTaxPercent(8.25);


        Tax tax2 = clientTax.getTaxByCategory(tax.getCategory());
        assertEquals(tax, tax2);

    }
}
