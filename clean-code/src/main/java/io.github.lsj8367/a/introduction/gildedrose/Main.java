package io.github.lsj8367.a.introduction.gildedrose;

public class Main {

    public static void main(String[] args) {

        final Item[] items = new Item[]{
            new Item("Default Item", 10, 20)
        };

        GildedRose app = new GildedRose(items);

        app.updateQuality();

        System.out.println(app);
    }

}
