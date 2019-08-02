package com.gatest.eureka.model;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class TaxRepo {

    @NotNull(message = "Please provide a category")
    private String category;
    private Double taxPercent;
    private Boolean taxExempt;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public Boolean getTaxExempt() {
        return taxExempt;
    }

    public void setTaxExempt(Boolean taxExempt) {
        this.taxExempt = taxExempt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxRepo taxRepo = (TaxRepo) o;
        return category.equals(taxRepo.category) &&
                taxPercent.equals(taxRepo.taxPercent) &&
                taxExempt.equals(taxRepo.taxExempt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, taxPercent, taxExempt);
    }
}
