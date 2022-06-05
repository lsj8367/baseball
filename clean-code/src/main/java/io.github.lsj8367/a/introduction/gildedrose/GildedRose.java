package io.github.lsj8367.a.introduction.gildedrose;

import java.util.Arrays;

class GildedRose {

    Item[] items;

    public GildedRose(final Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (final Item item : items) {
            agedBrieOrBackStagePasses(item);

            // Everything except for Sulfuras the sellIn Decreases
            item.isNotEqualSulfurasThenSellinMinus1();

            if (item.isExpired()) {
                isNameAgedBrieThenQualityPlus(item);
            }
        }
    }

    private void isNameAgedBrieThenQualityPlus(final Item item) {
        if (item.isNotEqualAgedBrie()) {
            backStagePassesThenQualityZero(item);
            return;
        }
        // For Aged Brie below 50 quality increases actually by 2
        // In the previous line
        item.isNotMaxQualityThenAddQuality();
    }

    private void backStagePassesThenQualityZero(final Item item) {
        if (item.isNotEqualBackStagePasses()) {
            item.isNotEqualSulfurasThenQualityMinus1();
            return;
        }
        // For Backstage passes with sellin less than zero
        // quality is set to zero
        item.zeroQuality();
    }

    private void agedBrieOrBackStagePasses(final Item item) {
        if (item.isNotEqualAgedBrie() && item.isNotEqualBackStagePasses()) {
            item.isNotEqualSulfurasThenQualityMinus1();
            return;
        }

        // This part handles the items for which quality can increase
        // "Backstage passes to a TAFKAL80ETC concert" and "Aged Brie"
        item.isBackStageThenIncreaseByCondition();
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

}