package terminal.adventure.game.inventory;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

public class EquipmentTest {

    private static class TestSlot extends Slot {
        public TestSlot() {
            super();
        }

        @Override
        public boolean canEquip(Item item) {
            return true;
        }
    }

    private static class TestStorageItem extends Item implements Storage {
        public TestStorageItem(String name, String description, Stats stats) {
            super(name, description, stats);
        }

        @Override
        public void addItem(Item item) {
        }

        @Override
        public void removeItem(Item item) {
        }

        @Override
        public List<Item> getItems() {
            return new ArrayList<>();
        }

        @Override
        public List<Item> searchItems(String itemName) {
            return new ArrayList<>();
        }

        @Override
        public void dump(Storage target) {
        }

        @Override
        public void clear() {
        }

        @Override
        public boolean contains(Item item) {
            return false;
        }
    }

    @Test
    public void testEquipItemWithSingleSlot() {
        List<Slot> slots = new ArrayList<>();
        slots.add(new TestSlot());
        Equipment equipment = new Equipment(slots);
        Item item = new Item("Sword", "A sharp sword", new Stats());
        boolean result = equipment.equipItem(item, null);
        assertTrue(result);
    }

    @Test
    public void testEquipItemWithNoMatchingSlot() {
        List<Slot> slots = new ArrayList<>();
        slots.add(new TestSlot() {
            @Override
            public boolean canEquip(Item item) {
                return false;
            }
        });
        Equipment equipment = new Equipment(slots);
        Item item = new Item("Sword", "A sharp sword", new Stats());
        boolean result = equipment.equipItem(item, null);
        assertFalse(result);
    }

    @Test
    public void testContainsStorageWithStorageItem() {
        List<Slot> slots = new ArrayList<>();
        TestSlot slot = new TestSlot();
        slots.add(slot);
        Equipment equipment = new Equipment(slots);
        TestStorageItem storageItem = new TestStorageItem("Backpack", "A backpack", new Stats());
        slot.equip(storageItem);
        assertTrue(equipment.containsStorage());
    }

    @Test
    public void testGetFirstStorage() {
        List<Slot> slots = new ArrayList<>();
        TestSlot slot = new TestSlot();
        slots.add(slot);
        Equipment equipment = new Equipment(slots);
        TestStorageItem storageItem = new TestStorageItem("Backpack", "A backpack", new Stats());
        slot.equip(storageItem);
        assertEquals(storageItem, equipment.getfirstStorage());
    }

    @Test
    public void testGetAllStorages() {
        List<Slot> slots = new ArrayList<>();
        TestSlot slot1 = new TestSlot();
        TestSlot slot2 = new TestSlot();
        slots.add(slot1);
        slots.add(slot2);
        Equipment equipment = new Equipment(slots);
        TestStorageItem storageItem1 = new TestStorageItem("Backpack1", "A backpack", new Stats());
        TestStorageItem storageItem2 = new TestStorageItem("Backpack2", "Another backpack", new Stats());
        slot1.equip(storageItem1);
        slot2.equip(storageItem2);
        List<Storage> storages = equipment.getAllStorages();
        assertEquals(2, storages.size());
    }

    @Test
    public void testTotalStats() {
        List<Slot> slots = new ArrayList<>();
        TestSlot slot = new TestSlot();
        slots.add(slot);
        Equipment equipment = new Equipment(slots);
        Stats itemStats = new Stats();
        itemStats.setStrength(10);
        Item item = new Item("Sword", "A sharp sword", itemStats);
        slot.equip(item);
        Stats totalStats = equipment.totalStats();
        assertEquals(10, totalStats.getStrength());
    }
}
