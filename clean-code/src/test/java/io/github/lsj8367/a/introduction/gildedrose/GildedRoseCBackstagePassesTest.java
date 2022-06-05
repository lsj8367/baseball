package io.github.lsj8367.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GildedRoseCBackstagePassesTest {

    @Test
    public void testUpdateQualityBackstagePasses1() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert",
            app.items[0].getName());
        assertEquals(14, app.items[0].getSellIn());
        assertEquals(4, app.items[0].getQuality());
    }

    @Test
    public void testUpdateQualityBackstagePasses2() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 7, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert",
            app.items[0].getName());
        assertEquals(6, app.items[0].getSellIn());
        assertEquals(5, app.items[0].getQuality());
    }

    @Test
    public void testUpdateQualityBackstagePasses3() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert",
            app.items[0].getName());
        assertEquals(3, app.items[0].getSellIn());
        assertEquals(6, app.items[0].getQuality());
    }

}