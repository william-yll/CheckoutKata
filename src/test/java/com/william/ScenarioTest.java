package com.william;


import com.william.checkout.Checkout;
import com.william.checkout.impl.CheckoutImpl;
import com.william.model.Item;
import com.william.repository.ItemRepository;
import com.william.repository.RuleRepository;
import com.william.repository.impl.ItemRepositoryImpl;
import com.william.repository.impl.RuleRepositoryImpl;
import com.william.rule.Rule;
import com.william.rule.impl.ManyForDiscountPriceRule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScenarioTest {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";

    @DisplayName("Scenario Tests")
    @ParameterizedTest
    @MethodSource("scenarios")
    public void scenarioTest(List<Item> items, List<Rule> rules, List<String> skus, String expectedPrice) {
        ItemRepository itemRepository = new ItemRepositoryImpl();
        itemRepository.add(items);
        RuleRepository ruleRepository = new RuleRepositoryImpl();
        ruleRepository.add(rules);

        Checkout checkout = new CheckoutImpl(itemRepository, ruleRepository);
        checkout.scan(skus);
        BigDecimal totalPrice = checkout.calculateTotal();

        assertEquals(new BigDecimal(expectedPrice), totalPrice);
    }

    private static Stream<Arguments> scenarios() {
        return Stream.of(
                Arguments.of(
                        List.of(new Item(A, new BigDecimal("50"))),
                        List.of(new ManyForDiscountPriceRule(A, 3, new BigDecimal("130"))),
                        List.of(A),
                        "50"),

                Arguments.of(
                        List.of(new Item(A, new BigDecimal("50")),
                                new Item(B, new BigDecimal("30"))),
                        List.of(new ManyForDiscountPriceRule(A, 3, new BigDecimal("130")),
                                new ManyForDiscountPriceRule(B, 2, new BigDecimal("45"))),
                        List.of(B, A, B),
                        "95"),

                Arguments.of(
                        List.of(new Item(A, new BigDecimal("50"))),
                        List.of(new ManyForDiscountPriceRule(A, 3, new BigDecimal("130"))),
                        List.of(A, A, A),
                        "130"),

                Arguments.of(
                        List.of(new Item(A, new BigDecimal("50")),
                                new Item(B, new BigDecimal("30"))),
                        List.of(new ManyForDiscountPriceRule(A, 3, new BigDecimal("130")),
                                new ManyForDiscountPriceRule(B, 2, new BigDecimal("45"))),
                        List.of(A, B, A, A),
                        "160"),

                Arguments.of(
                        List.of(new Item(A, new BigDecimal("50")),
                                new Item(B, new BigDecimal("30"))),
                        List.of(new ManyForDiscountPriceRule(A, 3, new BigDecimal("130")),
                                new ManyForDiscountPriceRule(B, 2, new BigDecimal("45"))),
                        List.of(A, B, A, A, B),
                        "175"),

                Arguments.of(
                        List.of(new Item(A, new BigDecimal("50")),
                                new Item(B, new BigDecimal("30")),
                                new Item(C, new BigDecimal("20")),
                                new Item(D, new BigDecimal("15"))),
                        List.of(new ManyForDiscountPriceRule(A, 3, new BigDecimal("130")),
                                new ManyForDiscountPriceRule(B, 2, new BigDecimal("45"))),
                        List.of(A, B, A, C, A, D, B),
                        "210")
        );
    }
}
