package com.william.repository;

import com.william.rule.Rule;

import java.util.Optional;

public interface RuleRepository extends Repository<Rule> {

    Optional<Rule> getRule(String sku);
}
