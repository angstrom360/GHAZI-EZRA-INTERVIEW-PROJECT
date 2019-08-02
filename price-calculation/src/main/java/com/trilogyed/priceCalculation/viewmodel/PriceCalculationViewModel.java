package com.trilogyed.priceCalculation.viewmodel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class PriceCalculationViewModel {


    private String productid;
    @NotEmpty(message = "Please enter a description.")
    private String description;
    @NotEmpty(message = "Please enter the quantity.")
    private int quantity;
    @NotNull(message = "Please supply a pricePerUnit")
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999999.99", inclusive = true)
    private Double pricePerUnit;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999999.99", inclusive = true)
    private Double taxPercent;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999999.99", inclusive = true)
    private Double totalTax;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "999999.99", inclusive = true)
    private Double total;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Double totalTax) {
        this.totalTax = totalTax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceCalculationViewModel that = (PriceCalculationViewModel) o;
        return quantity == that.quantity &&
                productid.equals(that.productid) &&
                description.equals(that.description) &&
                pricePerUnit.equals(that.pricePerUnit) &&
                taxPercent.equals(that.taxPercent) &&
                totalTax.equals(that.totalTax) &&
                total.equals(that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productid, description, quantity, pricePerUnit, taxPercent, totalTax, total);
    }
}
