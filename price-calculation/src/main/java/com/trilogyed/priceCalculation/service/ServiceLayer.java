package com.trilogyed.priceCalculation.service;


import com.trilogyed.priceCalculation.util.feign.ProductApi;
import com.trilogyed.priceCalculation.util.feign.TaxApi;
import com.trilogyed.priceCalculation.model.Tax;
import com.trilogyed.priceCalculation.model.Product;
import com.trilogyed.priceCalculation.viewmodel.PriceCalculationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;



@Component
@RefreshScope
public class ServiceLayer {


    /*----------- Importing all objects and variables that will be used to build the ServiceLayer ----------------- */


    TaxApi taxApi;

    ProductApi productApi;

    @Autowired
    public ServiceLayer(TaxApi taxApi, ProductApi productApi)
    {
        this.taxApi = taxApi;
        this.productApi = productApi;
    }
    /*Business Logic Requirement No.1:
     Calculate Total Price & Total Tax: */

    @HystrixCommand(fallbackMethod = "FallBackMethodCalculatePriceNoTaxExempt")
    public PriceCalculationViewModel calculatePrice (String productId, int quantity, Boolean taxExempt )
    {
        Product products = productApi.getProductByProductId(productId);
        Tax taxes = taxApi.getTaxByCategory(products.getCategory());
        PriceCalculationViewModel priceCalculationViewModel = new PriceCalculationViewModel();
        priceCalculationViewModel.setProductid(products.getProductId());
        priceCalculationViewModel.setDescription(products.getProductDescription());
        priceCalculationViewModel.setQuantity(quantity);
        priceCalculationViewModel.setPricePerUnit(products.getPricePerUnit());
        priceCalculationViewModel.setTaxPercent(taxes.getTaxPercent());

        if(taxes.getTaxExempt() == true && taxExempt ==true){
            priceCalculationViewModel.setTotalTax(0.00);
        }else{
            priceCalculationViewModel.setTotalTax(    // Total Tax: (PricePerUnit)x(TaxPercent)x(0.001)
                    priceCalculationViewModel.getPricePerUnit()*
                            priceCalculationViewModel.getTaxPercent()*0.01);
        }
        priceCalculationViewModel.setTotal(            //Total Price: (Quantity)x(PricePerUnit) + (TotalTax)
                priceCalculationViewModel.getQuantity()
                        * priceCalculationViewModel.getPricePerUnit()
                        + priceCalculationViewModel.getTotalTax());

        return priceCalculationViewModel;
    }

    /*-------- CIRCUIT BREAKER: FALL BACK METHOD FOR IF THE SERVER CRASHES -------------- */
    private PriceCalculationViewModel FallBackMethodCalculatePriceNoTaxExempt (String productId, int quantity, Boolean taxExempt)
    {
        PriceCalculationViewModel priceCalculationViewModel = new PriceCalculationViewModel();
        priceCalculationViewModel.setProductid(productId);
        priceCalculationViewModel.setDescription("No Description");
        priceCalculationViewModel.setQuantity(quantity);
        priceCalculationViewModel.setPricePerUnit(0.00);
        priceCalculationViewModel.setTaxPercent(0.00);
        priceCalculationViewModel.setTotalTax(0.00);
        priceCalculationViewModel.setTotal(0.00);

        return priceCalculationViewModel;

    }
    /*--------------------------------------------------------------------------------------------------------*/


}
