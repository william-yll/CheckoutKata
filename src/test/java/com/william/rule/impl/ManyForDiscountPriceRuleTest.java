package com.william.rule.impl;

import com.william.rule.Rule;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManyForDiscountPriceRuleTest {

    @Test
    public void testApplyOffer() {
        Rule rule = new ManyForDiscountPriceRule("A", 3, new BigDecimal("130"));

        BigDecimal price = rule.apply(4, new BigDecimal("50"));

        assertEquals(new BigDecimal("180"), price);
    }

    @Test
    public void testNotApplyOffer() {
        Rule rule = new ManyForDiscountPriceRule("A", 3, new BigDecimal("130"));

        BigDecimal price = rule.apply(1, new BigDecimal("50"));

        assertEquals(new BigDecimal("50"), price);
    }

    @Test
    public void testGetSku() {
        Rule rule = new ManyForDiscountPriceRule("A", 3, new BigDecimal("130"));

        String sku = rule.getSku();

        assertEquals("A", sku);
    }
}
