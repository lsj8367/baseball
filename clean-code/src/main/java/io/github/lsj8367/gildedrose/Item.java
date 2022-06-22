package io.github.lsj8367.gildedrose;

import java.util.Objects;

public class Item {

    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    private final String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public void zeroQuality() {
        quality = 0;
    }

    public void isNotEqualSulfurasThenQualityMinus1() {
        if (isMoreThanZeroQuality() && isNotEqualSulfuras()) {
            minusQuality();
        }
    }

    private boolean isMoreThanZeroQuality() {
        return quality > 0;
    }

    public void isNotEqualSulfurasThenSellinMinus1() {
        if (isNotEqualSulfuras()) {
            minusSellin();
        }
    }

    public void isBackStageThenIncreaseByCondition() {
        if (!isLessThanMaxValue()) {
            return;
        }

        quality += backStageCondition();
    }

    private boolean isLessThanMaxValue() {
        return quality < 50;
    }

    private int backStageCondition() {
        if (isNotEqualBackStagePasses()) {
            return 1;
        }

        if (5 <= sellIn && sellIn <= 10) {
            return 2;
        }

        if (sellIn < 5) {
            return 3;
        }

        return 1;
    }

    public boolean isExpired() {
        return sellIn < 0;
    }

    public void isNotMaxQualityThenAddQuality() {
        if (isLessThanMaxValue()) {
            addQuality();
        }
    }

    private void addQuality() {
        quality += 1;
    }

    private void minusQuality() {
        quality -= 1;
    }

    private void minusSellin() {
        sellIn -= 1;
    }

    private boolean isNotEqualSulfuras() {
        return !SULFURAS_HAND_OF_RAGNAROS.equals(name);
    }

    public boolean isNotEqualAgedBrie() {
        return !AGED_BRIE.equals(name);
    }

    public boolean isNotEqualBackStagePasses() {
        return !BACKSTAGE_PASSES.equals(name);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Item item = (Item) o;
        return sellIn == item.sellIn && quality == item.quality && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellIn, quality);
    }

    @Override
    public String toString() {
        return "\n name=" + name + ", " + sellIn + ", " + quality + "\n";
    }

}
