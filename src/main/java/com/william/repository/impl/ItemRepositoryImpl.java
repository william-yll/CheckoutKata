package com.william.repository.impl;

import com.william.exception.DuplicateException;
import com.william.exception.InvalidItemException;
import com.william.model.Item;
import com.william.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ItemRepositoryImpl implements ItemRepository {

    private final Map<String, BigDecimal> itemData;

    public ItemRepositoryImpl() {
        this.itemData = new HashMap<>();
    }

    @Override
    public void add(final List<Item> items) {
        items.forEach(this::add);
    }

    @Override
    public void add(final Item item) {
        if (exists(item)) {
            throw new DuplicateException("Duplicate item: " + item.getSku());
        }
        itemData.put(item.getSku(), item.getUnitPrice());
    }

    @Override
    public boolean exists(final Item item) {
        return Optional.ofNullable(itemData.get(item.getSku())).isPresent();
    }

    @Override
    public BigDecimal getPrice(final String sku) {
        return Optional.ofNullable(itemData.get(sku))
                .orElseThrow(() -> new InvalidItemException("Invalid item: " + sku));
    }
}
