package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.BackpackSlot;
import terminal.adventure.game.inventory.slots.Slot;

// Real Storage implementation for functional testing
class FunctionalStorage implements Storage {
    private final List<Item> items = new ArrayList<>();
    
    public FunctionalStorage() {
    }
    
    public FunctionalStorage(List<Item> initialItems) {
        this.items.addAll(initialItems);
    }
    
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
        return new ArrayList<>(items);
    }
    
    @Override
    public List<Item> searchItems(String itemName) {
        List<Item> results = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                results.add(item);
            }
        }
        return results;
    }
    
    @Override
    public void dump(Storage target) {
        for (Item item : new ArrayList<>(items)) {
            target.addItem(item);
        }
        items.clear();
    }
    
    @Override
    public void clear() {
        items.clear();
    }
    
    @Override
    public boolean contains(Item item) {
        return items.contains(item);
    }
    
    public int size() {
        return items.size();
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
}

// Real Equipment implementation for functional testing
class FunctionalEquipment extends Equipment {
    private final List<Slot> availableSlots;
    private final List<Item> equippedItems = new ArrayList<>();
    
    public FunctionalEquipment(List<Slot> slots) {
        super(slots);
        this.availableSlots = slots;
    }
    
    @Override
    public boolean equipItem(Item item, Controller controller) {
        // Functional logic:
        // 1. Can't equip null items
        // 2. Can't equip if no slots available
        // 3. Can equip swords, armor, and rings (with controller)
        // 4. Potions go to backpack if available
        
        if (item == null) {
            return false;
        }
        
        String itemName = item.getName().toLowerCase();
        
        // Check if we have any slots
        if (availableSlots.isEmpty()) {
            return false;
        }
        
        // Check item type
        if (itemName.contains("sword")) {
            // Need a weapon slot or any slot
            equippedItems.add(item);
            return true;
        } else if (itemName.contains("armor")) {
            // Need armor slot or any slot
            equippedItems.add(item);
            return true;
        } else if (itemName.contains("potion")) {
            // Check for backpack slot
            for (Slot slot : availableSlots) {
                if (slot instanceof BackpackSlot) {
                    equippedItems.add(item);
                    return true;
                }
            }
            return false;
        } else if (itemName.contains("ring")) {
            // Rings need controller to choose slot
            if (controller != null) {
                equippedItems.add(item);
                return true;
            }
            return false;
        } else {
            // Unknown item type
            return false;
        }
    }
    
    public List<Item> getEquippedItems() {
        return new ArrayList<>(equippedItems);
    }
    
    public boolean isEquipped(Item item) {
        return equippedItems.contains(item);
    }
}

// Functional Controller for testing
class FunctionalController extends Controller {
    private int chooseSlotCallCount = 0;
    private List<String> lastCandidateNames = null;
    
    public FunctionalController() {
        super(null);
    }
    
    @Override
    public void bindActor(Actor actor) {
    }
    
    @Override
    public void unbindActor() {
    }
    
    @Override
    public Actor getActor() {
        return null;
    }
    
    @Override
    public void die() {
    }

    @Override
    public int equipChooseSlot(List<String> candidatesNames) {
        chooseSlotCallCount++;
        lastCandidateNames = candidatesNames;
        // For testing, always choose the first option
        return 0;
    }

    @Override
    protected void play() {
    }

    @Override
    public void takeAttackReport(Stats report) {
    }
    
    public int getChooseSlotCallCount() {
        return chooseSlotCallCount;
    }
    
    public List<String> getLastCandidateNames() {
        return lastCandidateNames;
    }
}

// Real Actor subclass for functional testing
class FunctionalActor extends Actor {
    private final FunctionalEquipment functionalEquipment;
    
     public FunctionalActor(String name, String description, FunctionalEquipment equipment) {
        super(name, description);
        this.functionalEquipment = equipment;
        initEquipmentField();
    }
    
    private void initEquipmentField() {
        try {
            java.lang.reflect.Field field = Actor.class.getDeclaredField("equipment");
            field.setAccessible(true);
            field.set(this, functionalEquipment);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("Actor class doesn't have 'equipment' field", e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Cannot access 'equipment' field", e);
        }
    }
    
    @Override
    public java.util.List<Slot> makeSlots() {
        // Return functional slots
        List<Slot> slots = new ArrayList<>();
        slots.add(new BackpackSlot()); // Always have a backpack
        return slots;
    }
    
    public FunctionalEquipment getFunctionalEquipment() {
        return functionalEquipment;
    }
}

public class ActorEquipFromTestfonct {
    
    private FunctionalActor actor;
    private FunctionalEquipment functionalEquipment;
    private FunctionalController controller;
    
    @Before
    public void setUp() {
        // Create functional equipment with slots
        List<Slot> slots = new ArrayList<>();
        slots.add(new BackpackSlot());
        functionalEquipment = new FunctionalEquipment(slots);
        
        // Create actor with functional equipment
        actor = new FunctionalActor("TestHero", "A brave hero", functionalEquipment);
        
        // Create functional controller
        controller = new FunctionalController();
    }
    
    @After
    public void tearDown() {
        // Cleanup if needed
    }
    
    // ==== FUNCTIONAL TESTS FOR equipFrom(Item item, Controller controller, Storage source) ====
    
    @Test
    public void testEquipFrom_Functional_SuccessfulEquipFromStorage() {
        // Create storage with items
        Item sword = new Item("Steel Sword", "A sharp steel sword", new Stats());
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(sword);
        
        // Verify initial state
        assertEquals("Storage should have 1 item", 1, storage.size());
        assertTrue("Storage should contain sword", storage.contains(sword));
        assertFalse("Sword should not be equipped yet", functionalEquipment.isEquipped(sword));
        
        // Execute equipFrom
        boolean result = actor.equipFrom(sword, controller, storage);
        
        // Verify
        assertTrue("equipFrom should succeed", result);
        assertTrue("Sword should now be equipped", functionalEquipment.isEquipped(sword));
        assertEquals("Storage should be empty after equip", 0, storage.size());
        assertFalse("Storage should not contain sword anymore", storage.contains(sword));
        assertEquals("Equipment should have 1 equipped item", 1, functionalEquipment.getEquippedItems().size());
    }
    
    @Test
    public void testEquipFrom_Functional_ItemNotInStorage_Fails() {
        // Create storage without the item
        Item sword = new Item("Sword", "A sword", new Stats());
        FunctionalStorage storage = new FunctionalStorage(); // Empty storage
        
        // Execute equipFrom
        boolean result = actor.equipFrom(sword, controller, storage);
        
        // Verify
        assertFalse("equipFrom should fail when item not in storage", result);
        assertFalse("Sword should not be equipped", functionalEquipment.isEquipped(sword));
        assertEquals("Storage should still be empty", 0, storage.size());
        assertEquals("Equipment should have no equipped items", 0, functionalEquipment.getEquippedItems().size());
    }
    
    @Test
    public void testEquipFrom_Functional_StorageWithMultipleItems_EquipSpecificOne() {
        // Create storage with multiple items
        Item sword = new Item("Sword", "A sword", new Stats());
        Item armor = new Item("Armor", "Heavy armor", new Stats());
        Item potion = new Item("Potion", "Health potion", new Stats());
        
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(sword);
        storage.addItem(armor);
        storage.addItem(potion);
        
        // Equip only the armor
        boolean result = actor.equipFrom(armor, controller, storage);
        
        // Verify
        assertTrue("Should equip armor successfully", result);
        assertTrue("Armor should be equipped", functionalEquipment.isEquipped(armor));
        assertFalse("Sword should not be equipped", functionalEquipment.isEquipped(sword));
        assertFalse("Potion should not be equipped", functionalEquipment.isEquipped(potion));
        
        // Verify storage state
        assertEquals("Storage should have 2 items left", 2, storage.size());
        assertTrue("Storage should still have sword", storage.contains(sword));
        assertFalse("Storage should not have armor anymore", storage.contains(armor));
        assertTrue("Storage should still have potion", storage.contains(potion));
    }
    
    @Test
    public void testEquipFrom_Functional_EquipFails_ItemNotRemovedFromStorage() {
        // Create an item that can't be equipped (unknown type)
        Item unknownItem = new Item("Mysterious Orb", "Cannot equip this", new Stats());
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(unknownItem);
        
        // Execute equipFrom - should fail because item can't be equipped
        boolean result = actor.equipFrom(unknownItem, controller, storage);
        
        // Verify
        assertFalse("equipFrom should fail for unknown item", result);
        assertFalse("Item should not be equipped", functionalEquipment.isEquipped(unknownItem));
        assertEquals("Storage should still have the item", 1, storage.size());
        assertTrue("Storage should still contain the item", storage.contains(unknownItem));
    }
    
    @Test
    public void testEquipFrom_Functional_RingRequiresController() {
        // Test that ring items require controller
        Item ring = new Item("Magic Ring", "A magical ring", new Stats());
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(ring);
        
        // Test with controller - should succeed
        boolean resultWithController = actor.equipFrom(ring, controller, storage);
        assertTrue("Ring should equip with controller", resultWithController);
        assertTrue("Ring should be equipped", functionalEquipment.isEquipped(ring));
        assertEquals("Storage should be empty", 0, storage.size());
        
        // Reset for second test
        storage.addItem(new Item("Another Ring", "Another ring", new Stats()));
        FunctionalEquipment newEquipment = new FunctionalEquipment(new ArrayList<>());
        FunctionalActor newActor = new FunctionalActor("NewActor", "Description", newEquipment);
        
        // Test without controller - should fail
        boolean resultWithoutController = newActor.equipFrom(ring, null, storage);
        assertFalse("Ring should not equip without controller", resultWithoutController);
        assertEquals("Storage should still have the ring", 1, storage.size());
    }
    
    @Test
    public void testEquipFrom_Functional_PotionGoesToBackpack() {
        // Test that potions go to backpack
        Item potion = new Item("Health Potion", "Restores health", new Stats());
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(potion);
        
        // Execute
        boolean result = actor.equipFrom(potion, controller, storage);
        
        // Verify
        assertTrue("Potion should equip to backpack", result);
        assertTrue("Potion should be equipped", functionalEquipment.isEquipped(potion));
        assertEquals("Storage should be empty", 0, storage.size());
    }
    
    @Test
    public void testEquipFrom_Functional_ItemStatsPreserved() {
        // Verify item stats are preserved after equip
        Stats detailedStats = new Stats();
        detailedStats.setStrength(20);
        detailedStats.setArmor(10);
        detailedStats.setCurrentHealth(100);
        
        Item powerfulItem = new Item("Legendary Sword", "Very powerful", detailedStats);
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(powerfulItem);
        
        // Execute
        boolean result = actor.equipFrom(powerfulItem, controller, storage);
        
        // Verify
        assertTrue("Should equip successfully", result);
        
        // Check that the item is the same instance with same stats
        List<Item> equipped = functionalEquipment.getEquippedItems();
        assertEquals("Should have 1 equipped item", 1, equipped.size());
        Item equippedItem = equipped.get(0);
        
        assertEquals("Should be same item", powerfulItem, equippedItem);
        assertEquals("Name should be preserved", "Legendary Sword", equippedItem.getName());
        assertEquals("Description should be preserved", "Very powerful", equippedItem.look());
        
        Stats retrievedStats = equippedItem.getStats();
        assertEquals("Strength should be preserved", 20, retrievedStats.getStrength());
        assertEquals("Armor should be preserved", 10, retrievedStats.getArmor());
        assertEquals("Health should be preserved", 100, retrievedStats.getCurrentHealth());
    }
    
    @Test
    public void testEquipFrom_Functional_StorageMethodsWorkCorrectly() {
        // Test that storage methods work as expected during equipFrom
        Item item1 = new Item("Sword 1", "First sword", new Stats());
        Item item2 = new Item("Sword 2", "Second sword", new Stats());
        
        FunctionalStorage storage = new FunctionalStorage();
        
        // Test addItem and contains
        storage.addItem(item1);
        assertTrue("Storage should contain item1", storage.contains(item1));
        assertFalse("Storage should not contain item2", storage.contains(item2));
        assertEquals("Storage should have 1 item", 1, storage.size());
        
        // Add second item
        storage.addItem(item2);
        assertEquals("Storage should have 2 items", 2, storage.size());
        
        // Test getItems
        List<Item> items = storage.getItems();
        assertEquals("getItems should return 2 items", 2, items.size());
        assertTrue("getItems should contain item1", items.contains(item1));
        assertTrue("getItems should contain item2", items.contains(item2));
        
        // Now equip one item
        boolean result = actor.equipFrom(item1, controller, storage);
        assertTrue("Should equip successfully", result);
        
        // Verify storage updated correctly
        assertEquals("Storage should have 1 item left", 1, storage.size());
        assertFalse("Storage should not contain item1 anymore", storage.contains(item1));
        assertTrue("Storage should still contain item2", storage.contains(item2));
        
        List<Item> remainingItems = storage.getItems();
        assertEquals("Remaining items should be 1", 1, remainingItems.size());
        assertEquals("Remaining item should be item2", item2, remainingItems.get(0));
    }
    
    @Test
    public void testEquipFrom_Functional_MultipleEquipsFromSameStorage() {
        // Equip multiple items from same storage
        Item sword = new Item("Sword", "A sword", new Stats());
        Item armor = new Item("Armor", "Armor", new Stats());
        Item potion = new Item("Potion", "Potion", new Stats());
        
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(sword);
        storage.addItem(armor);
        storage.addItem(potion);
        
        // Equip all items
        boolean result1 = actor.equipFrom(sword, controller, storage);
        boolean result2 = actor.equipFrom(armor, controller, storage);
        boolean result3 = actor.equipFrom(potion, controller, storage);
        
        // Verify
        assertTrue("All equips should succeed", result1 && result2 && result3);
        assertEquals("All items should be equipped", 3, functionalEquipment.getEquippedItems().size());
        assertTrue("Storage should be empty", storage.isEmpty());
        
        // Verify all items are equipped
        assertTrue("Sword should be equipped", functionalEquipment.isEquipped(sword));
        assertTrue("Armor should be equipped", functionalEquipment.isEquipped(armor));
        assertTrue("Potion should be equipped", functionalEquipment.isEquipped(potion));
    }
    
    @Test
    public void testEquipFrom_Functional_StorageEmptyAfterLastEquip() {
        // Test that storage becomes empty after last item is equipped
        Item lastItem = new Item("Last Sword", "The last sword", new Stats());
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(lastItem);
        
        // Verify initial state
        assertFalse("Storage should not be empty initially", storage.isEmpty());
        
        // Equip the item
        boolean result = actor.equipFrom(lastItem, controller, storage);
        
        // Verify final state
        assertTrue("Equip should succeed", result);
        assertTrue("Storage should be empty", storage.isEmpty());
        assertEquals("Storage size should be 0", 0, storage.size());
        assertTrue("Item should be equipped", functionalEquipment.isEquipped(lastItem));
    }
    
    @Test
    public void testEquipFrom_Functional_ItemReferenceEquality() {
        // Test that the exact same item instance is equipped
        Item originalItem = new Item("Unique Sword", "One of a kind", new Stats());
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(originalItem);
        
        // Create another item with same properties (but different instance)
        Item similarItem = new Item("Unique Sword", "One of a kind", new Stats());
        
        // Verify they're different instances
        assertNotSame("Items should be different instances", originalItem, similarItem);
        
        // Equip the original item
        boolean result = actor.equipFrom(originalItem, controller, storage);
        assertTrue("Should equip successfully", result);
        
        // Verify the exact instance is equipped
        List<Item> equipped = functionalEquipment.getEquippedItems();
        assertEquals("Should have 1 equipped item", 1, equipped.size());
        assertSame("Should be the exact same instance", originalItem, equipped.get(0));
        assertNotSame("Should not be the similar item", similarItem, equipped.get(0));
    }
    
    @Test
    public void testEquipFrom_Functional_ControllerInteraction() {
        // Test that controller methods are called appropriately
        Item ring = new Item("Ring", "Needs controller", new Stats());
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(ring);
        
        // Equip the ring
        boolean result = actor.equipFrom(ring, controller, storage);
        
        // Verify
        assertTrue("Should equip successfully", result);
        assertEquals("Controller chooseSlot should be called", 1, controller.getChooseSlotCallCount());
        assertNotNull("Controller should receive candidate names", controller.getLastCandidateNames());
        
        // For non-ring items, controller shouldn't be called
        Item sword = new Item("Sword", "Doesn't need controller", new Stats());
        storage.addItem(sword);
        
        int previousCallCount = controller.getChooseSlotCallCount();
        actor.equipFrom(sword, controller, storage);
        assertEquals("Controller should not be called for sword", previousCallCount, controller.getChooseSlotCallCount());
    }
    
    @Test
    public void testEquipFrom_Functional_EdgeCases() {
        // Test various edge cases
        
        // 1. Null item
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(new Item("Test", "Test", new Stats()));
        
        boolean result = actor.equipFrom(null, controller, storage);
        assertFalse("Should fail with null item", result);
        
        // 2. Null controller (for non-ring items)
        Item sword = new Item("Sword", "A sword", new Stats());
        storage.clear();
        storage.addItem(sword);
        
        result = actor.equipFrom(sword, null, storage);
        assertTrue("Sword should equip even without controller", result);
        
        // 3. Empty storage
        FunctionalStorage emptyStorage = new FunctionalStorage();
        result = actor.equipFrom(sword, controller, emptyStorage);
        assertFalse("Should fail with empty storage", result);
    }
    
    @Test
    public void testEquipFrom_Functional_IntegrationWithOtherStorageMethods() {
        // Test integration with other Storage interface methods
        
        // Create storage with items
        Item item1 = new Item("Item1", "First", new Stats());
        Item item2 = new Item("Item2", "Second", new Stats());
        
        FunctionalStorage source = new FunctionalStorage();
        source.addItem(item1);
        source.addItem(item2);
        
        // Test searchItems
        List<Item> searchResults = source.searchItems("Item1");
        assertEquals("Should find 1 item", 1, searchResults.size());
        assertEquals("Should find item1", item1, searchResults.get(0));
        
        // Equip one item
        boolean result = actor.equipFrom(item1, controller, source);
        assertTrue("Should equip successfully", result);
        
        // Verify searchItems still works for remaining items
        searchResults = source.searchItems("Item2");
        assertEquals("Should find 1 item", 1, searchResults.size());
        assertEquals("Should find item2", item2, searchResults.get(0));
        
        searchResults = source.searchItems("Item1");
        assertEquals("Should not find removed item", 0, searchResults.size());
        
        // Test dump method
        FunctionalStorage target = new FunctionalStorage();
        source.dump(target);
        assertTrue("Source should be empty after dump", source.isEmpty());
        assertEquals("Target should have 1 item", 1, target.size());
        
        // Test clear method
        target.clear();
        assertTrue("Target should be empty after clear", target.isEmpty());
    }
    
    @Test
    public void testEquipFrom_Functional_PerformanceWithManyItems() {
        // Test performance with many items in storage
        FunctionalStorage storage = new FunctionalStorage();
        List<Item> items = new ArrayList<>();
        
        // Add 50 items to storage
        for (int i = 0; i < 50; i++) {
            Item item = new Item("Item" + i, "Item number " + i, new Stats());
            items.add(item);
            storage.addItem(item);
        }
        
        assertEquals("Storage should have 50 items", 50, storage.size());
        
        // Equip 10 specific items
        int equippedCount = 0;
        for (int i = 0; i < 10; i++) {
            boolean result = actor.equipFrom(items.get(i * 5), controller, storage); // Equip every 5th item
            if (result) equippedCount++;
        }
        
        // Verify
        assertEquals("Should equip 10 items", 10, equippedCount);
        assertEquals("Storage should have 40 items left", 40, storage.size());
        assertEquals("Equipment should have 10 items", 10, functionalEquipment.getEquippedItems().size());
    }
    
    @Test
    public void testEquipFrom_Functional_StateConsistency() {
        // Test that system remains consistent after multiple operations
        Item item1 = new Item("Item1", "First", new Stats());
        Item item2 = new Item("Item2", "Second", new Stats());
        Item item3 = new Item("Item3", "Third", new Stats());
        
        FunctionalStorage storage1 = new FunctionalStorage();
        FunctionalStorage storage2 = new FunctionalStorage();
        
        storage1.addItem(item1);
        storage1.addItem(item2);
        storage2.addItem(item3);
        
        // Equip from storage1
        boolean result1 = actor.equipFrom(item1, controller, storage1);
        assertTrue("First equip should succeed", result1);
        
        // Verify state
        assertEquals("Storage1 should have 1 item", 1, storage1.size());
        assertEquals("Storage2 should have 1 item", 1, storage2.size());
        assertEquals("Equipment should have 1 item", 1, functionalEquipment.getEquippedItems().size());
        
        // Equip from storage2
        boolean result2 = actor.equipFrom(item3, controller, storage2);
        assertTrue("Second equip should succeed", result2);
        
        // Verify state
        assertTrue("Storage2 should be empty", storage2.isEmpty());
        assertEquals("Equipment should have 2 items", 2, functionalEquipment.getEquippedItems().size());
        
        // Try to equip item1 again (should fail - already equipped)
        boolean result3 = actor.equipFrom(item1, controller, storage1);
        assertFalse("Should fail - item1 not in storage1", result3);
        
        // Final state verification
        assertEquals("Storage1 should still have 1 item (item2)", 1, storage1.size());
        assertTrue("Storage1 should contain item2", storage1.contains(item2));
        assertTrue("Storage2 should be empty", storage2.isEmpty());
        assertEquals("Equipment should have 2 items", 2, functionalEquipment.getEquippedItems().size());
        assertTrue("Equipment should have item1", functionalEquipment.isEquipped(item1));
        assertTrue("Equipment should have item3", functionalEquipment.isEquipped(item3));
        assertFalse("Equipment should not have item2", functionalEquipment.isEquipped(item2));
    }
    
    @Test
    public void testEquipFrom_Functional_ItemRemovedOnlyOnSuccessfulEquip() {
        // Critical test: item should only be removed from storage if equip succeeds
        Item canEquip = new Item("Sword", "Can equip", new Stats());
        Item cannotEquip = new Item("Unknown", "Cannot equip", new Stats());
        
        FunctionalStorage storage = new FunctionalStorage();
        storage.addItem(canEquip);
        storage.addItem(cannotEquip);
        
        // Try to equip the item that can't be equipped
        boolean result1 = actor.equipFrom(cannotEquip, controller, storage);
        assertFalse("Should fail to equip unknown item", result1);
        assertEquals("Storage should still have 2 items", 2, storage.size());
        assertTrue("CannotEquip should still be in storage", storage.contains(cannotEquip));
        
        // Now equip the item that can be equipped
        boolean result2 = actor.equipFrom(canEquip, controller, storage);
        assertTrue("Should succeed to equip sword", result2);
        assertEquals("Storage should have 1 item left", 1, storage.size());
        assertTrue("CannotEquip should still be in storage", storage.contains(cannotEquip));
        assertFalse("CanEquip should be removed from storage", storage.contains(canEquip));
    }
}