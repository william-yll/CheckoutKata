package com.william.model;

import java.math.BigDecimal;

public class Item {

    private final String sku;
    private final BigDecimal unitPrice;

    public Item(final String sku, final BigDecimal unitPrice) {
        this.sku = sku;
        this.unitPrice = unitPrice;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "sku='" + sku + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
