package io.github.lsj8367.a.introduction.gildedrose;

public class Item {

    private final String name;

    private int sellIn;

    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void addQuality() {
        quality += 1;
    }

    public void minusQuality() {
        quality -= 1;
    }

    public void minusSellin() {
        sellIn -= 1;
    }

    public void zeroQuality() {
        quality = 0;
    }

    @Override
    public String toString() {
        return "\n name=" + name + ", " + sellIn + ", " + quality + "\n";
    }

}
