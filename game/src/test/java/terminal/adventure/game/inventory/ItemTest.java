package terminal.adventure.game.inventory;

import org.junit.Test;
import static org.junit.Assert.*;
import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.items.Item;

public class ItemTest {

    @Test
    public void testItemConstructor() {
        Stats stats = new Stats();
        stats.setStrength(10);
        stats.setSpeed(5);
        Item item = new Item("Sword", "A sharp sword", stats);
        assertEquals("Sword", item.getName());
        assertEquals("A sharp sword", item.look());
        assertEquals(stats, item.getStats());
    }

    @Test
    public void testGetName() {
        Stats stats = new Stats();
        Item item = new Item("Shield", "A sturdy shield", stats);
        assertEquals("Shield", item.getName());
    }

    @Test
    public void testLook() {
        Stats stats = new Stats();
        Item item = new Item("Potion", "A healing potion", stats);
        assertEquals("A healing potion", item.look());
    }

    @Test
    public void testGetStats() {
        Stats stats = new Stats();
        stats.setStrength(15);
        stats.setSpeed(10);
        Item item = new Item("Bow", "A powerful bow", stats);
        assertEquals(stats, item.getStats());
    }

    @Test
    public void testToString() {
        Stats stats = new Stats();
        Item item = new Item("Helmet", "A protective helmet", stats);
        assertEquals("Item : (Item) Helmet\n", item.toString());
    }
}
