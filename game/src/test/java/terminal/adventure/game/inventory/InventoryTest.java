package terminal.adventure.game.inventory;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.Stats;

public class InventoryTest {

    private static class TestStorage implements Storage {
        private final List<Item> items = new java.util.ArrayList<>();

        @Override
        public void addItem(Item item) {
            items.add(item);
        }

        @Override
        public void removeItem(Item item) {
            items.remove(item);
        }

        @Override
        public List<Item> getItems() {
            return items;
        }

        @Override
        public List<Item> searchItems(String itemName) {
            return null;
        }

        @Override
        public void dump(Storage target) {
        }

        @Override
        public void clear() {
            items.clear();
        }

        @Override
        public boolean contains(Item item) {
            return items.contains(item);
        }
    }

    @Test
    public void testInventoryConstructor() {
        Inventory inventory = new Inventory();
        assertTrue(inventory.getItems().isEmpty());
    }

    @Test
    public void testAddItem() {
        Inventory inventory = new Inventory();
        Item item = new Item("Sword", "A sharp sword", new Stats());
        inventory.add(item);
        assertEquals(1, inventory.getItems().size());
        assertEquals(item, inventory.getItems().get(0));
    }

    @Test
    public void testRemoveItem() {
        Inventory inventory = new Inventory();
        Item item = new Item("Sword", "A sharp sword", new Stats());
        inventory.add(item);
        inventory.remove(item);
        assertTrue(inventory.getItems().isEmpty());
    }

    @Test
    public void testSearchItemsByName() {
        Inventory inventory = new Inventory();
        Item sword = new Item("Sword", "A sharp sword", new Stats());
        Item shield = new Item("Shield", "A sturdy shield", new Stats());
        inventory.add(sword);
        inventory.add(shield);
        List<Item> foundItems = inventory.searchItemsByName("Sword");
        assertEquals(1, foundItems.size());
        assertEquals(sword, foundItems.get(0));
    }

    @Test
    public void testTakeItem() {
        Inventory inventory = new Inventory();
        TestStorage source = new TestStorage();
        Item item = new Item("Potion", "A healing potion", new Stats());
        source.addItem(item);
        boolean result = inventory.takeItem(source, item);
        assertTrue(result);
        assertEquals(1, inventory.getItems().size());
        assertEquals(item, inventory.getItems().get(0));
        assertTrue(source.getItems().isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void testTakeItemWithNullSource() {
        Inventory inventory = new Inventory();
        Item item = new Item("Potion", "A healing potion", new Stats());
        inventory.takeItem(null, item);
    }

    @Test(expected = NullPointerException.class)
    public void testTakeItemWithNullItem() {
        Inventory inventory = new Inventory();
        TestStorage source = new TestStorage();
        inventory.takeItem(source, null);
    }

    @Test
    public void testTakeItemNotInSource() {
        Inventory inventory = new Inventory();
        TestStorage source = new TestStorage();
        Item item = new Item("Potion", "A healing potion", new Stats());
        boolean result = inventory.takeItem(source, item);
        assertFalse(result);
        assertTrue(inventory.getItems().isEmpty());
    }
}