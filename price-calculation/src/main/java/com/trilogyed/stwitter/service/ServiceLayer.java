package com.trilogyed.stwitter.service;


import com.trilogyed.stwitter.model.Tax;
import com.trilogyed.stwitter.model.Product;
import com.trilogyed.stwitter.util.feign.TaxApi;
import com.trilogyed.stwitter.util.feign.ProductApi;
import com.trilogyed.stwitter.viewmodel.PriceCalculationViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component
@RefreshScope
public class ServiceLayer {
    /*----------------------------- Importing RabbitTemplate Variables and keys ----------------*/
/*
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.#";
*/

    /*------------------------------------------------------------------------------------*/


    /*----------- Importing all objects and variables that will be used to build the ServiceLayer ----------------- */

    @Autowired
    TaxApi taxApi;
    @Autowired
    ProductApi productApi;

    @Autowired
    public ServiceLayer(TaxApi taxApi, ProductApi productApi)
    {
        this.taxApi = taxApi;
        this.productApi = productApi;
    }
    private PriceCalculationViewModel buildPriceCalculationViewModel (Product product)
    {
        PriceCalculationViewModel priceCalculationViewModel = new PriceCalculationViewModel();
        priceCalculationViewModel.setProductid(product.getProductId());
        priceCalculationViewModel.setDescription(product.getProductDescription());
        priceCalculationViewModel.setPricePerUnit(product.getPricePerUnit());
        Tax tax = new Tax();

        priceCalculationViewModel.setTaxPercent(tax.);
        commentViewModel.setCommentContent(tax.getComment());
        return commentViewModel;
    }
    private PriceCalculationViewModel buildPriceCalculationViewModel (PriceCalculationViewModel priceCalculationViewModel)
    {
        PriceCalculationViewModel priceCalculationViewModel = new PriceCalculationViewModel();
        Product product = new Product();
        priceCalculationViewModel.setProductid(product.getProductId());
        priceCalculationViewModel.set(product.getPriceCalculation());
        priceCalculationViewModel.setPriceCalculationDate(product.getPriceCalculationDate());
        priceCalculationViewModel.setPriceCalculationerName(product.getPriceCalculationerName());
        priceCalculationViewModel.setComments(taxApi.getCommentsByPriceCalculationId(product.getId()));

        return priceCalculationViewModel;
    }

    /*--------------------------------------------------------------------------------------------------------*/

    /*=============================== TWITTER SERVICE BUSINESS LOGIC METHODS =================================*/

    @Transactional
    public PriceCalculationViewModel createPriceCalculation(PriceCalculationViewModel priceCalculationViewModel) //BUSINESS LOGIC REQUIREMENT NO. 1
    {
        Product product = new Product();
        product.setProductId(priceCalculationViewModel.getProductid());
        product.setProductDescription(priceCalculationViewModel.getDescription());
        product.setPricePerUnit(priceCalculationViewModel.getPricePerUnit());
        product.setCategory(productApi.);
        product = productApi.createPriceCalculation(product);
        priceCalculationViewModel.setPriceCalculationId(product.getId());
        List<Tax> taxList = priceCalculationViewModel.getComments();

        taxList = taxApi.getCommentsByPriceCalculationId(product.getId());
        priceCalculationViewModel.setComments(taxList);
        return priceCalculationViewModel;
    }

    public PriceCalculationViewModel getPriceCalculation(int id) //BUSINESS LOGIC REQUIREMENT NO. 2
    {
        return buildPriceCalculationViewModel(productApi.getPriceCalculation(id));
    }

    public List<PriceCalculationViewModel> getPriceCalculationByPriceCalculationerName(String priceCalculationerName) //BUSINESS LOGIC REQUIREMENT NO. 3
    {
        List<PriceCalculationViewModel> priceCalculationViewModel = new ArrayList<>();

        productApi.getPriceCalculationForPriceCalculationer(priceCalculationerName)
                .forEach(product -> priceCalculationViewModel
                        .add(buildPriceCalculationViewModel(product)));


        return priceCalculationViewModel;
    }

    /*========================================================================================================*/



}
