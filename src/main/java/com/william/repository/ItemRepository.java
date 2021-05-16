package com.william.repository;

import com.william.model.Item;

import java.math.BigDecimal;

public interface ItemRepository extends Repository<Item> {

    BigDecimal getPrice(String sku);
}
