package com.trilogyed.priceCalculation.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Product {
    private String productId;
//    @NotNull(message = "Please supply an Product Description.")
    private String productDescription;
//    @NotNull(message = "Please supply the pricePerUnit.")
//    @DecimalMin(value = "0.0", inclusive = true)
//    @DecimalMax(value = "999999.99", inclusive = true)
    private Double pricePerUnit;
//    @NotEmpty(message = "Please supply an category.")
    private String category;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId.equals(product.productId) &&
                productDescription.equals(product.productDescription) &&
                pricePerUnit.equals(product.pricePerUnit) &&
                category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productDescription, pricePerUnit, category);
    }
}