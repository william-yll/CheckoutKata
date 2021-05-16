package com.william.rule;

import java.math.BigDecimal;

public interface Rule {

    BigDecimal apply(long totalQuantity, BigDecimal unitPrice);

    String getSku();
}
