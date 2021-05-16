package com.william.rule.impl;

import com.william.rule.Rule;

import java.math.BigDecimal;

public class ManyForDiscountPriceRule implements Rule {

    private final String sku;
    private final long bulkQuantity;
    private final BigDecimal discountedPrice;

    public ManyForDiscountPriceRule(final String sku, final long bulkQuantity, final BigDecimal discountedPrice) {
        this.sku = sku;
        this.bulkQuantity = bulkQuantity;
        this.discountedPrice = discountedPrice;
    }

    /**
     * Calculates price of sku item based on rule
     *
     * @param totalQuantity quantity of sku items
     * @param unitPrice     unit price of sku item
     * @return price of sku items
     */
    @Override
    public BigDecimal apply(final long totalQuantity, final BigDecimal unitPrice) {
        long quantity = totalQuantity;
        BigDecimal updatedPrice = BigDecimal.ZERO;
        while (quantity >= bulkQuantity) {
            updatedPrice = updatedPrice.add(discountedPrice);
            quantity -= bulkQuantity;
        }
        if (quantity > 0) {
            updatedPrice = updatedPrice.add(new BigDecimal(quantity).multiply(unitPrice));
        }
        return updatedPrice;

    }

    @Override
    public String getSku() {
        return sku;
    }

    @Override
    public String toString() {
        return "ManyForDiscountPriceRule{" +
                "sku='" + sku + '\'' +
                ", bulkQuantity=" + bulkQuantity +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
