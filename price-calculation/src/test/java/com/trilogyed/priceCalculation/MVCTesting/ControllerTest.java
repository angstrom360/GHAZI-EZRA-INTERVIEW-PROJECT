package com.trilogyed.priceCalculation.MVCTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.priceCalculation.controller.PriceCalculationController;
import com.trilogyed.priceCalculation.model.Product;
import com.trilogyed.priceCalculation.model.Tax;
import com.trilogyed.priceCalculation.service.ServiceLayer;
import com.trilogyed.priceCalculation.util.feign.ProductApi;
import com.trilogyed.priceCalculation.util.feign.TaxApi;
import com.trilogyed.priceCalculation.viewmodel.PriceCalculationViewModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceCalculationController.class)
public class ControllerTest {

    @MockBean
    DataSource dataSource;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductApi productApi;

    @MockBean
    private TaxApi taxApi;

    @MockBean
    private ServiceLayer service;

    private ObjectMapper mapper = new ObjectMapper();
   /* @Test
    public void test_get_by_id_success() throws Exception {
        PriceCalculationController pvm = new PostViewModel(1, LocalDate.now(),"LOLO", "HI");

        when(serviceLayer.calculatePrice("Blue",3,true)).thenReturn(pvm);
        ObjectMapper mapper = new ObjectMapper();
        String outputjson = mapper.writeValueAsString(pvm);

        mockMvc.perform(get("/post/{id}", 1))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()
                .andExpect(content().json(outputjson));

    }*/


    @Test
    public void getRsvpByIdShouldReturnRsvpWithIdJson() throws Exception {
        Product product = new Product();
        product.setProductId("123");
        product.setProductDescription("Animal");
        product.setPricePerUnit(1.00);
        product.setCategory("Food");
//        product = productApi.createProduct(product);

        Tax tax = new Tax();
        tax.setCategory("Food");
        tax.setTaxPercent(0.00);
        tax.setTaxExempt(false);
//        tax = taxApi.createTax(tax);

        PriceCalculationViewModel pvm = new PriceCalculationViewModel();        // Total Tax: (PricePerUnit)x(TaxPercent)x(0.001)
        pvm.setProductid(product.getProductId());
        pvm.setDescription(product.getProductDescription());
        pvm.setQuantity(1);
        pvm.setPricePerUnit(product.getPricePerUnit());
        pvm.setTotalTax(pvm.getPricePerUnit() * tax.getTaxPercent() * 0.001);
        pvm.setTotal(pvm.getPricePerUnit() * pvm.getQuantity() + pvm.getTotalTax());

        //Object to JSON in String
        String outputJson = mapper.writeValueAsString(pvm);
        when(service.calculatePrice("123", 1, false)).thenReturn(pvm);
        this.mockMvc.perform(get("/api/price/product/123?quantity=1&taxExempt=false")).andDo(print()).andExpect(status().isOk())
                .andExpect(content()
                        //Below - construct the json "manually"
                        /*   .json("{'id':8," +
                                   "'guestName':'Mock Gruber'}")
                        )*/
                        //Below - use the objectmapper output with the json method
                        .json(outputJson));
        //Below - use the objectmapper output with the string/containsString
        //.string(containsString(outputJson)));
        // this may fail because the order in the outputJson may not match the result
        // even though they're technically equals
        // the json { "id":99,"name":"Dan" } is technically equal to { "name":"Dan","id" }
        // but the strings of the above are not equal to each other
    }
}