package io.github.lsj8367.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GildedRoseADefaultItemRefactoredTest {

    private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final int NOT_EXPIRED_SELL_IN = 15;
    private static final int EXPIRED_SELL_IN = -1;
    private static final int DEFAULT_QUALITY = 4;

    @Test
    @DisplayName("아이템이 만료되지 않은 경우 판매갯수가 1이상이면 판매 1 감소, 품질 1 감소")
    void itemNotExpiredThenDecreaseSellInAndQuality1() {
        GildedRose app = createGildedRose(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);
        app.updateQuality();
        Item actual = app.items[0];
        Item expected = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 1);

        assertItem(actual, expected);
    }

    @Test
    @DisplayName("아이템이 만료되고 판매갯수가 0 미만이면 품질 2감소, 판매 1 감소")
    void itemIsExpiredThenDecreaseQuality2SellIn1() {
        GildedRose app = createGildedRose(DEFAULT_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 2);

        assertItem(actual, expected);
    }

    private void assertItem(final Item actual, final Item expected) {
        assertEquals(expected.name, actual.name);
        assertEquals(expected.sellIn, actual.sellIn);
        assertEquals(expected.quality, actual.quality);
    }

    private GildedRose createGildedRose(final String itemName, final int sellIn, final int quality) {
        Item item = new Item(DEFAULT_ITEM, sellIn, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

}