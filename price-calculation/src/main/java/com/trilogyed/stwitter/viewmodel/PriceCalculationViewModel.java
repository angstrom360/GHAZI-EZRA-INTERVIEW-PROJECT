package com.trilogyed.stwitter.viewmodel;

import java.math.BigDecimal;
import java.util.Objects;

public class PriceCalculationViewModel {

    private String productid;
    private String description;
    private int quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal taxPercent;
    private BigDecimal totalTax;
    private BigDecimal total;

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

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(BigDecimal taxPercent) {
        this.taxPercent = taxPercent;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
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
