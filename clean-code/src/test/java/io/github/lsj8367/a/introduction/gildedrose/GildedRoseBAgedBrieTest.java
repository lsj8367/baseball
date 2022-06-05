package io.github.lsj8367.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GildedRoseBAgedBrieTest {

    @Test
    public void testUpdateQualityAgedBrie1() {
        Item item = new Item("Aged Brie", 4, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].getName());
        assertEquals(3, app.items[0].getSellIn());
        assertEquals(4, app.items[0].getQuality());
    }

    @Test
    public void testUpdateQualityAgedBrie2() {
        Item item = new Item("Aged Brie", -1, 3);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].getName());
        assertEquals(-2, app.items[0].getSellIn());
        assertEquals(5, app.items[0].getQuality());
    }

    @Test
    public void testUpdateQualityAgedBrie3() {
        Item item = new Item("Aged Brie", 4, 50);
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].getName());
        assertEquals(3, app.items[0].getSellIn());
        assertEquals(50, app.items[0].getQuality());
    }

}
