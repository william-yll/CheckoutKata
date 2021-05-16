package com.william.repository.impl;

import com.william.exception.DuplicateException;
import com.william.repository.RuleRepository;
import com.william.rule.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RuleRepositoryImpl implements RuleRepository {

    private final Map<String, Rule> ruleData;

    public RuleRepositoryImpl() {
        this.ruleData = new HashMap<>();
    }

    @Override
    public void add(final List<Rule> rules) {
        rules.forEach(this::add);
    }

    @Override
    public void add(final Rule rule) {
        if (exists(rule)) {
            throw new DuplicateException("Duplicate rule: " + rule.getSku());
        }
        ruleData.put(rule.getSku(), rule);
    }

    @Override
    public boolean exists(final Rule rule) {
        return Optional.ofNullable(ruleData.get(rule.getSku())).isPresent();
    }

    @Override
    public Optional<Rule> getRule(final String sku) {
        return Optional.ofNullable(ruleData.get(sku));
    }

}
