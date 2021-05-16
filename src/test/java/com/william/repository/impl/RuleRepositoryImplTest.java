package com.william.repository.impl;

import com.william.exception.DuplicateException;
import com.william.repository.RuleRepository;
import com.william.rule.Rule;
import com.william.rule.impl.ManyForDiscountPriceRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RuleRepositoryImplTest {

    private RuleRepository ruleRepository;

    @BeforeEach
    public void setup() {
        ruleRepository = new RuleRepositoryImpl();
    }

    @Test
    public void testAddSingleRule() {
        Rule rule = new ManyForDiscountPriceRule("A", 3, new BigDecimal("130"));
        ruleRepository.add(rule);

        assertTrue(ruleRepository.exists(rule));
    }

    @Test
    public void testAddListRules() {
        Rule rule1 = new ManyForDiscountPriceRule("A", 3, new BigDecimal("130"));
        Rule rule2 = new ManyForDiscountPriceRule("B", 2, new BigDecimal("45"));
        List<Rule> ruleList = List.of(rule1, rule2);

        ruleRepository.add(ruleList);

        assertTrue(ruleRepository.exists(rule1));
        assertTrue(ruleRepository.exists(rule2));
    }

    @Test
    public void testAddDuplicateRuleThrowsDuplicateException() {
        Rule rule1 = new ManyForDiscountPriceRule("A", 3, new BigDecimal("130"));
        Rule rule2 = new ManyForDiscountPriceRule("A", 4, new BigDecimal("130"));
        List<Rule> ruleList = List.of(rule1, rule2);

        assertThrows(DuplicateException.class, () -> ruleRepository.add(ruleList));
    }

    @Test
    public void getRuleAvailable() {
        Rule rule = new ManyForDiscountPriceRule("A", 3, new BigDecimal("130"));
        ruleRepository.add(rule);

        Optional<Rule> ruleOptional = ruleRepository.getRule("A");

        assertTrue(ruleOptional.isPresent());
    }

    @Test
    public void getRuleNotAvailable() {
        Optional<Rule> ruleOptional = ruleRepository.getRule("A");

        assertFalse(ruleOptional.isPresent());
    }
}
