package io.github.lsj8367.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.lsj8367.gildedrose.GildedRose;
import io.github.lsj8367.gildedrose.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GildedRoseRefactoredTest {

    private static final String DEFAULT_ITEM = "DEFAULT_ITEM";
    private static final int NOT_EXPIRED_SELL_IN = 15;
    private static final int EXPIRED_SELL_IN = -1;
    private static final int DEFAULT_QUALITY = 4;
    public static final String AGED_BRIE = "Aged Brie";
    public static final int MAX_QUALITY = 50;
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SELL_IN_MORE_THAN_10_DAYS = 15;
    public static final int SELL_IN_MORE_5_LESS_10_DAYS = 9;
    public static final int SELL_IN_LESS_5_DAYS = 4;

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

    @Test
    @DisplayName("sellin이 10일 이상이면 quality 1증가")
    void backStageMoreThan10DaysQualityPlus2() {
        GildedRose app = createGildedRoseOneItem(BACKSTAGE_PASSES, SELL_IN_MORE_THAN_10_DAYS, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(BACKSTAGE_PASSES, SELL_IN_MORE_THAN_10_DAYS - 1, DEFAULT_QUALITY + 1);
        assertItem(actual, expected);
    }

    @Test
    @DisplayName("sellin이 5일 이상 10일 이하이면 quality 2증가")
    void backStageMore5Less10DaysQualityPlus2() {
        GildedRose app = createGildedRoseOneItem(BACKSTAGE_PASSES, SELL_IN_MORE_5_LESS_10_DAYS, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(BACKSTAGE_PASSES, SELL_IN_MORE_5_LESS_10_DAYS - 1, DEFAULT_QUALITY + 2);
        assertItem(actual, expected);
    }

    @Test
    @DisplayName("sellin이 5일 이하이면 quality 3증가")
    void backStageLess5DaysQualityPlus3() {
        GildedRose app = createGildedRoseOneItem(BACKSTAGE_PASSES, SELL_IN_LESS_5_DAYS, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(BACKSTAGE_PASSES, SELL_IN_LESS_5_DAYS - 1, DEFAULT_QUALITY + 3);
        assertItem(actual, expected);
    }

    @Test
    @DisplayName("sellin이 만료 됐으면 quality 0")
    void expiredBackStageQualityZero() {
        GildedRose app = createGildedRoseOneItem(BACKSTAGE_PASSES, EXPIRED_SELL_IN, DEFAULT_QUALITY);
        app.updateQuality();

        Item actual = app.items[0];
        Item expected = new Item(BACKSTAGE_PASSES, EXPIRED_SELL_IN - 1, 0);
        assertItem(actual, expected);
    }

    private GildedRose createGildedRoseOneItem(final String itemName, final int sellIn, final int quality) {
        Item item = new Item(itemName, sellIn, quality);
        Item[] items = new Item[]{item};
        return new GildedRose(items);
    }

    private void assertItem(final Item actual, final Item expected) {
        assertThat(actual).isEqualTo(expected);
    }

}