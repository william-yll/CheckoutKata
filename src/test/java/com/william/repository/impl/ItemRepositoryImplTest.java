package com.william.repository.impl;

import com.william.exception.DuplicateException;
import com.william.exception.InvalidItemException;
import com.william.model.Item;
import com.william.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemRepositoryImplTest {

    private ItemRepository itemRepository;

    @BeforeEach
    public void setup() {
        itemRepository = new ItemRepositoryImpl();
    }

    @Test
    public void testAddSingleItem() {
        Item item = new Item("A", new BigDecimal("50"));

        itemRepository.add(item);

        assertTrue(itemRepository.exists(item));
    }

    @Test
    public void testAddListItems() {
        Item item1 = new Item("A", new BigDecimal("50"));
        Item item2 = new Item("B", new BigDecimal("30"));
        Item item3 = new Item("C", new BigDecimal("20"));
        List<Item> itemList = List.of(item1, item2, item3);

        itemRepository.add(itemList);

        assertTrue(itemRepository.exists(item1));
        assertTrue(itemRepository.exists(item2));
        assertTrue(itemRepository.exists(item3));
    }

    @Test
    public void testAddDuplicateItemThrowsDuplicateException() {
        Item item1 = new Item("A", new BigDecimal("50"));
        Item item2 = new Item("A", new BigDecimal("30"));
        List<Item> itemList = List.of(item1, item2);

        assertThrows(DuplicateException.class, () -> itemRepository.add(itemList));
    }

    @Test
    public void testGetPrice() {
        Item item = new Item("A", new BigDecimal("50"));
        itemRepository.add(item);

        BigDecimal price = itemRepository.getPrice("A");

        assertEquals(new BigDecimal("50"), price);
    }

    @Test
    public void testGetPriceWithInvalidItemThrowsInvalidItemException() {
        Item item = new Item("A", new BigDecimal("50"));
        itemRepository.add(item);

        assertThrows(InvalidItemException.class, () -> itemRepository.getPrice("B"));
    }


}
