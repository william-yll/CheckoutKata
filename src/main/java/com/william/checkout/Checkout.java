package com.william.checkout;

import java.math.BigDecimal;
import java.util.List;

public interface Checkout {

    void scan(List<String> skus);

    void scan(String sku);

    BigDecimal calculateTotal();
}
