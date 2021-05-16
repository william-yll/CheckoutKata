package com.william.checkout.impl;

import com.william.checkout.Checkout;
import com.william.repository.ItemRepository;
import com.william.repository.RuleRepository;
import com.william.rule.Rule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class CheckoutImpl implements Checkout {

    private static final Logger LOG = Logger.getLogger(CheckoutImpl.class.getName());

    private final ItemRepository itemRepository;
    private final RuleRepository ruleRepository;
    private final Map<String, Long> skus;

    public CheckoutImpl(final ItemRepository itemRepository, final RuleRepository ruleRepository) {
        this.itemRepository = itemRepository;
        this.ruleRepository = ruleRepository;
        this.skus = new HashMap<>();
    }

    @Override
    public void scan(final List<String> skus) {
        skus.forEach(this::scan);
    }

    public void scan(final String sku) {
        LOG.info(String.format("Scanning item: %s", sku));
        itemRepository.getPrice(sku);
        skus.merge(sku, 1L, Long::sum);
    }

    /**
     * Calculates the total price for all the items scanned
     * @return total price of items
     */
    public BigDecimal calculateTotal() {
        LOG.info("Calculating total price");
        return skus.entrySet().stream()
                .map(entry -> {
                    Optional<Rule> ruleOptional = ruleRepository.getRule(entry.getKey());
                    if (ruleOptional.isPresent()) {
                        return ruleOptional.get().apply(entry.getValue(), itemRepository.getPrice(entry.getKey()));
                    } else {
                        return new BigDecimal(entry.getValue()).multiply(itemRepository.getPrice(entry.getKey()));
                    }
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
