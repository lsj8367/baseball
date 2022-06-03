package io.github.lsj8367.a.introduction.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GildedRoseRefactoredTest {

    private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final int NOT_EXPIRED_SELL_IN = 15;
    private static final int EXPIRED_SELL_IN = -1;
    private static final int DEFAULT_QUALITY = 4;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY = 50;

    @Test
    @DisplayName("아이템이 만료되지 않은 경우 판매갯수가 1이상이면 판매 1 감소, 품질 1 감소")
    void itemNotExpiredThenDecreaseSellInAndQuality1() {
        GildedRose app = createGildedRoseOneItem(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);
        app.updateQuality();
        Item actual = app.items[0];
        Item expected = new Item(DEFAULT_ITEM, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 1);

        assertItem(actual, expected);
    }

    @Test
    @DisplayName("아이템이 만료되고 판매갯수가 0 미만이면 품질 2감소, 판매 1 감소")
    void itemIsExpiredThenDecreaseQuality2SellIn1() {
        GildedRose app = createGildedRoseOneItem(DEFAULT_ITEM, EXPIRED_SELL_IN, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(DEFAULT_ITEM, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY - 2);

        assertItem(actual, expected);
    }

    @Test
    @DisplayName("sellin이 1이상이라 만료되지 않고 quality 1증가")
    void unexpiredAgedBrieQualityPlus1() {
        final GildedRose app = createGildedRoseOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 1);
        assertItem(actual, expected);
    }

    @Test
    @DisplayName("sellin이 만료(-1)되면 quality 2증가")
    void expiredAgedBrieQualityPlus2() {
        final GildedRose app = createGildedRoseOneItem(AGED_BRIE, EXPIRED_SELL_IN, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(AGED_BRIE, EXPIRED_SELL_IN - 1, DEFAULT_QUALITY + 2);
        assertItem(actual, expected);
    }

    @Test
    @DisplayName("quality가 50이면 더이상 증가하지 않음")
    void unexpiredAgedBrieQualityDoNotIncrease() {
        final GildedRose app = createGildedRoseOneItem(AGED_BRIE, NOT_EXPIRED_SELL_IN, MAX_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(AGED_BRIE, NOT_EXPIRED_SELL_IN - 1, MAX_QUALITY);
        assertItem(actual, expected);
    }

    private GildedRose createGildedRoseOneItem(final String itemName, final int sellIn, final int quality) {
        Item item = new Item(itemName, sellIn, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

    private void assertItem(final Item actual, final Item expected) {
        assertThat(actual.name).isEqualTo(expected.name);
        assertThat(actual.sellIn).isEqualTo(expected.sellIn);
        assertThat(actual.quality).isEqualTo(expected.quality);
    }

}