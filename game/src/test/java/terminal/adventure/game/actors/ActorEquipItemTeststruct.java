package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import terminal.adventure.game.Location;
import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

// Mock Location implementing Storage
class MockLocation extends Location implements Storage {
    private List<Item> items = new ArrayList<>();
    private int searchItemsCallCount = 0;
    private String lastSearchedName = null;
    private int removeItemCallCount = 0;
    private Item lastRemovedItem = null;
    
    public MockLocation() {
        super("Test Location", "A test location");
    }
    
    @Override
    public List<Item> searchItems(String itemName) {
        searchItemsCallCount++;
        lastSearchedName = itemName;
        List<Item> results = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                results.add(item);
            }
        }
        return results;
    }
    
    @Override
    public void removeItem(Item item) {
        removeItemCallCount++;
        lastRemovedItem = item;
        items.remove(item);
    }
    
    @Override
    public void addItem(Item item) {
        items.add(item);
    }
    
    @Override
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    @Override
    public boolean contains(Item item) {
        return items.contains(item);
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
    
    public int getSearchItemsCallCount() {
        return searchItemsCallCount;
    }
    
    public String getLastSearchedName() {
        return lastSearchedName;
    }
    
    public int getRemoveItemCallCount() {
        return removeItemCallCount;
    }
    
    public Item getLastRemovedItem() {
        return lastRemovedItem;
    }
    
    public void addTestItem(Item item) {
        items.add(item);
    }
}

// Mock Equipment implementing getAllStorages
class MockEquipmentWithStorage extends Equipment {
    private List<Storage> storages = new ArrayList<>();
    private boolean equipItemResult;
    private Item lastEquippedItem;
    private Controller lastUsedController;
    private int equipCallCount = 0;
    
    public MockEquipmentWithStorage(List<Slot> slots, List<Storage> storages) {
        super(slots);
        this.storages = storages;
    }
    
    @Override
    public List<Storage> getAllStorages() {
        return new ArrayList<>(storages);
    }
    
    @Override
    public boolean equipItem(Item item, Controller controller) {
        this.equipCallCount++;
        this.lastEquippedItem = item;
        this.lastUsedController = controller;
        return equipItemResult;
    }
    
    public void setEquipItemResult(boolean result) {
        this.equipItemResult = result;
    }
    
    public Item getLastEquippedItem() {
        return lastEquippedItem;
    }
    
    public Controller getLastUsedController() {
        return lastUsedController;
    }
    
    public int getEquipCallCount() {
        return equipCallCount;
    }
}

// Mock Storage for equipment
class MockEquipmentStorage implements Storage {
    private List<Item> items = new ArrayList<>();
    private int searchItemsCallCount = 0;
    private String lastSearchedName = null;
    private int removeItemCallCount = 0;
    private Item lastRemovedItem = null;
    
    @Override
    public List<Item> searchItems(String itemName) {
        searchItemsCallCount++;
        lastSearchedName = itemName;
        List<Item> results = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                results.add(item);
            }
        }
        return results;
    }
    
    @Override
    public void removeItem(Item item) {
        removeItemCallCount++;
        lastRemovedItem = item;
        items.remove(item);
    }
    
    @Override
    public void addItem(Item item) {
        items.add(item);
    }
    
    @Override
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    @Override
    public boolean contains(Item item) {
        return items.contains(item);
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
    
    public int getSearchItemsCallCount() {
        return searchItemsCallCount;
    }
    
    public String getLastSearchedName() {
        return lastSearchedName;
    }
    
    public int getRemoveItemCallCount() {
        return removeItemCallCount;
    }
    
    public Item getLastRemovedItem() {
        return lastRemovedItem;
    }
    
    public void addTestItem(Item item) {
        items.add(item);
    }
}

// Mock Controller
class MockController extends Controller {
    public MockController() {
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
        return 0;
    }

    @Override
    protected void play() {
    }

    @Override
    public void takeAttackReport(Stats report) {
    }
}

// Actor subclass for testing
class TestActorForEquipItem extends Actor {
    private MockEquipmentWithStorage mockEquipment;
    
    public TestActorForEquipItem(String name, String description, MockEquipmentWithStorage equipment) {
        super(name, description);
        this.mockEquipment = equipment;
        try {
            java.lang.reflect.Field field = Actor.class.getDeclaredField("equipment");
            field.setAccessible(true);
            field.set(this, equipment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set mock equipment", e);
        }
    }
    
    @Override
    public java.util.List<Slot> makeSlots() {
        return new ArrayList<>();
    }
    
    public MockEquipmentWithStorage getMockEquipment() {
        return mockEquipment;
    }
}

public class ActorEquipItemTeststruct {
    
    private TestActorForEquipItem actor;
    private MockEquipmentWithStorage mockEquipment;
    private MockController controller;
    private MockEquipmentStorage storage1;
    private MockEquipmentStorage storage2;
    private MockLocation location;
    private Item item;
    
    @Before
    public void setUp() {
        // Create storages
        storage1 = new MockEquipmentStorage();
        storage2 = new MockEquipmentStorage();
        
        // Create equipment with storages
        List<Storage> storages = new ArrayList<>();
        storages.add(storage1);
        storages.add(storage2);
        mockEquipment = new MockEquipmentWithStorage(new ArrayList<>(), storages);
        
        // Create actor
        actor = new TestActorForEquipItem("TestActor", "Description", mockEquipment);
        
        // Create controller and item
        controller = new MockController();
        item = new Item("TestItem", "A test item", new Stats());
        
        // Create location
        location = new MockLocation();
    }
    
    @After
    public void tearDown() {
        // Cleanup
    }
    
    // ==== STRUCTURAL TESTS FOR equipItem(String itemName, Controller controller) ====
    
    // Test 1: Item found in first storage, equip succeeds → returns true, item removed
    @Test
    public void testEquipItem_ItemInFirstStorage_EquipSucceeds_ReturnsTrue_ItemRemoved() {
        // Setup
        storage1.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actor.equipItem("TestItem", controller);
        
        // Verifications
        assertTrue("Should return true when item found and equip succeeds", result);
        assertEquals("storage1.searchItems called once", 1, storage1.getSearchItemsCallCount());
        assertEquals("storage1 searched for correct name", "TestItem", storage1.getLastSearchedName());
        assertEquals("storage2.searchItems NOT called (short-circuit)", 0, storage2.getSearchItemsCallCount());
        assertEquals("Equipment.equipItem called once", 1, mockEquipment.getEquipCallCount());
        assertEquals("Correct item passed to equip", item, mockEquipment.getLastEquippedItem());
        assertEquals("Correct controller passed", controller, mockEquipment.getLastUsedController());
        assertEquals("storage1.removeItem called", 1, storage1.getRemoveItemCallCount());
        assertEquals("Correct item removed", item, storage1.getLastRemovedItem());
    }
    
    // Test 2: Item found in first storage, equip fails → returns false, item NOT removed
    @Test
    public void testEquipItem_ItemInFirstStorage_EquipFails_ReturnsFalse_ItemNotRemoved() {
        // Setup
        storage1.addTestItem(item);
        mockEquipment.setEquipItemResult(false);
        
        // Execution
        boolean result = actor.equipItem("TestItem", controller);
        
        // Verifications
        assertFalse("Should return false when equip fails", result);
        assertEquals("storage1.searchItems called once", 1, storage1.getSearchItemsCallCount());
        assertEquals("Equipment.equipItem called once", 1, mockEquipment.getEquipCallCount());
        assertEquals("storage1.removeItem NOT called", 0, storage1.getRemoveItemCallCount());
    }
    
    // Test 3: Item not in first storage, found in second storage, equip succeeds
    @Test
    public void testEquipItem_ItemInSecondStorage_EquipSucceeds() {
        // Setup - item in second storage only
        storage2.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actor.equipItem("TestItem", controller);
        
        // Verifications
        assertTrue("Should return true", result);
        assertEquals("storage1.searchItems called once", 1, storage1.getSearchItemsCallCount());
        assertEquals("storage2.searchItems called once", 1, storage2.getSearchItemsCallCount());
        assertEquals("Equipment.equipItem called once", 1, mockEquipment.getEquipCallCount());
        assertEquals("storage2.removeItem called", 1, storage2.getRemoveItemCallCount());
    }
    
    // Test 4: Item not in equipment storages, found in location, equip succeeds
    @Test
    public void testEquipItem_ItemInLocation_EquipSucceeds() {
        // Setup
        location.addTestItem(item);
        actor.setLocation(location);
        mockEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actor.equipItem("TestItem", controller);
        
        // Verifications
        assertTrue("Should return true", result);
        assertEquals("storage1.searchItems called once", 1, storage1.getSearchItemsCallCount());
        assertEquals("storage2.searchItems called once", 1, storage2.getSearchItemsCallCount());
        assertEquals("location.searchItems called once", 1, location.getSearchItemsCallCount());
        assertEquals("Equipment.equipItem called once", 1, mockEquipment.getEquipCallCount());
        assertEquals("location.removeItem called", 1, location.getRemoveItemCallCount());
    }
    
    // Test 5: Item not found anywhere → returns false
    @Test
    public void testEquipItem_ItemNotFound_ReturnsFalse() {
        // Setup - no items anywhere
        mockEquipment.setEquipItemResult(true); // Would succeed if item found
        
        // Execution
        boolean result = actor.equipItem("NonExistentItem", controller);
        
        // Verifications
        assertFalse("Should return false when item not found", result);
        assertEquals("storage1.searchItems called once", 1, storage1.getSearchItemsCallCount());
        assertEquals("storage2.searchItems called once", 1, storage2.getSearchItemsCallCount());
        assertEquals("Equipment.equipItem NOT called", 0, mockEquipment.getEquipCallCount());
    }
    
    // Test 6: Multiple candidates found, uses first candidate
    @Test
    public void testEquipItem_MultipleCandidates_UsesFirst() {
        // Setup - multiple items with same name
        Item item1 = new Item("Sword", "First sword", new Stats());
        Item item2 = new Item("Sword", "Second sword", new Stats());
        storage1.addTestItem(item1);
        storage1.addTestItem(item2);
        mockEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actor.equipItem("Sword", controller);
        
        // Verifications
        assertTrue("Should return true", result);
        assertEquals("Equipment.equipItem called with first item", item1, mockEquipment.getLastEquippedItem());
        assertEquals("First item removed", item1, storage1.getLastRemovedItem());
        assertFalse("First item should be removed", storage1.getItems().contains(item1));
        assertTrue("Second item should still be in storage", storage1.getItems().contains(item2));
    }
    
    // Test 7: Null itemName parameter
    @Test
    public void testEquipItem_NullItemName() {
        // Setup
        storage1.addTestItem(item);
        
        // Execution with null itemName
        boolean result = actor.equipItem(null, controller);
        
        // Verifications
        assertFalse("Should return false with null itemName", result);
        assertEquals("storage1.searchItems called with null", 1, storage1.getSearchItemsCallCount());
        assertNull("Null should be passed to searchItems", storage1.getLastSearchedName());
    }
    
    // Test 8: Null controller parameter
    @Test
    public void testEquipItem_NullController() {
        // Setup
        storage1.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Execution with null controller
        boolean result = actor.equipItem("TestItem", null);
        
        // Verifications
        assertTrue("Should succeed even with null controller", result);
        assertNull("Null controller should be passed to equip", mockEquipment.getLastUsedController());
        assertEquals("Item should be removed", 1, storage1.getRemoveItemCallCount());
    }
    
    // Test 9: No location set (location is null)
    @Test
    public void testEquipItem_NoLocation_OnlyEquipmentStoragesSearched() {
        // Setup - actor has no location
        actor.setLocation(null);
        location.addTestItem(item); // Item only in location, but location not accessible
        
        // Execution
        boolean result = actor.equipItem("TestItem", controller);
        
        // Verifications
        assertFalse("Should return false - item only in inaccessible location", result);
        assertEquals("storage1.searchItems called", 1, storage1.getSearchItemsCallCount());
        assertEquals("storage2.searchItems called", 1, storage2.getSearchItemsCallCount());
        // Location.searchItems should not be called because location is null
    }
    
    // Test 10: Empty equipment storages list
    @Test
    public void testEquipItem_NoEquipmentStorages_OnlyLocationSearched() {
        // Create equipment with empty storages list
        MockEquipmentWithStorage emptyEquipment = new MockEquipmentWithStorage(
            new ArrayList<>(), 
            new ArrayList<>() // Empty storages
        );
        TestActorForEquipItem actorWithNoStorages = 
            new TestActorForEquipItem("NoStorageActor", "Description", emptyEquipment);
        
        // Setup - item only in location
        location.addTestItem(item);
        actorWithNoStorages.setLocation(location);
        emptyEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actorWithNoStorages.equipItem("TestItem", controller);
        
        // Verifications
        assertTrue("Should find item in location", result);
        assertEquals("location.searchItems called", 1, location.getSearchItemsCallCount());
        assertEquals("location.removeItem called", 1, location.getRemoveItemCallCount());
    }
    
    // Test 11: searchItems returns empty list, continues to next source
    @Test
    public void testEquipItem_SearchReturnsEmpty_ContinuesToNextSource() {
        // Setup - item in second storage only
        storage2.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actor.equipItem("TestItem", controller);
        
        // Verifications
        assertTrue("Should find item in second storage", result);
        assertEquals("storage1.searchItems called", 1, storage1.getSearchItemsCallCount());
        assertEquals("storage2.searchItems called", 1, storage2.getSearchItemsCallCount());
        assertTrue("Result should be true", result);
    }
    
    // Test 12: searchItems returns multiple items, uses first, fails, returns false (no continue)
    @Test
    public void testEquipItem_MultipleItems_FirstFails_ReturnsFalse_NoContinue() {
        // Setup - multiple items, first equip fails
        Item item1 = new Item("Sword", "First", new Stats());
        Item item2 = new Item("Sword", "Second", new Stats());
        storage1.addTestItem(item1);
        storage1.addTestItem(item2);
        mockEquipment.setEquipItemResult(false); // Equip will fail
        
        // Execution
        boolean result = actor.equipItem("Sword", controller);
        
        // Verifications
        assertFalse("Should return false", result);
        assertEquals("Equipment.equipItem called once (with first item)", 1, mockEquipment.getEquipCallCount());
        assertEquals("Called with first item", item1, mockEquipment.getLastEquippedItem());
        assertEquals("storage1.searchItems called once", 1, storage1.getSearchItemsCallCount());
        // Should NOT continue to storage2 because return false immediately
    }
    
    // Test 13: Order of source checking: equipment storages first, then location
    @Test
    public void testEquipItem_OrderOfSourceChecking() {
        // Setup - same item in both storage1 and location
        storage1.addTestItem(item);
        location.addTestItem(new Item("TestItem", "Different instance", new Stats()));
        actor.setLocation(location);
        mockEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actor.equipItem("TestItem", controller);
        
        // Verifications - should use item from storage1, not location
        assertTrue("Should succeed", result);
        assertEquals("storage1.searchItems called", 1, storage1.getSearchItemsCallCount());
        assertEquals("storage2.searchItems called", 1, storage2.getSearchItemsCallCount());
        // Location.searchItems should NOT be called because item found in equipment storage
        assertEquals("location.searchItems NOT called (found in equipment)", 0, location.getSearchItemsCallCount());
        assertEquals("storage1.removeItem called", 1, storage1.getRemoveItemCallCount());
        assertEquals("location.removeItem NOT called", 0, location.getRemoveItemCallCount());
    }
    
    // Test 14: Case sensitivity in search
    @Test
    public void testEquipItem_CaseSensitivity() {
        // Setup
        storage1.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Execution with different case
        boolean result = actor.equipItem("testitem", controller); // lowercase
        
        // Verifications - depends on searchItems implementation
        // Our mock is case-insensitive, so should find it
        assertEquals("storage1.searchItems called", 1, storage1.getSearchItemsCallCount());
        assertEquals("Searched with lowercase", "testitem", storage1.getLastSearchedName());
    }
    
    // Test 15: searchItems throws exception
    @Test(expected = RuntimeException.class)
    public void testEquipItem_SearchItemsThrowsException_ExceptionPropagated() {
        // Create storage that throws exception
        MockEquipmentStorage throwingStorage = new MockEquipmentStorage() {
            @Override
            public List<Item> searchItems(String itemName) {
                super.searchItems(itemName);
                throw new RuntimeException("Test exception from searchItems");
            }
        };
        
        // Create equipment with throwing storage
        List<Storage> storages = new ArrayList<>();
        storages.add(throwingStorage);
        MockEquipmentWithStorage throwingEquipment = new MockEquipmentWithStorage(new ArrayList<>(), storages);
        TestActorForEquipItem throwingActor = 
            new TestActorForEquipItem("ThrowingActor", "Description", throwingEquipment);
        
        // This should propagate the exception
        throwingActor.equipItem("TestItem", controller);
    }
    
    // Test 16: equip throws exception after successful search
    @Test(expected = RuntimeException.class)
    public void testEquipItem_EquipThrowsException_ExceptionPropagated() {
        // Setup
        storage1.addTestItem(item);
        
        // Create equipment that throws exception
        List<Storage> storagesList = new ArrayList<>();
        storagesList.add(storage1);
        
        MockEquipmentWithStorage throwingEquipment = new MockEquipmentWithStorage(
            new ArrayList<>(), 
            storagesList
        ) {
            @Override
            public boolean equipItem(Item item, Controller controller) {
                // Call parent to track the call
                super.equipItem(item, controller);
                // Always throw exception
                throw new RuntimeException("Test exception from equipItem");
            }
        };
        
        TestActorForEquipItem throwingActor = 
            new TestActorForEquipItem("ThrowingActor", "Description", throwingEquipment);
        
        // This should propagate the exception
        throwingActor.equipItem("TestItem", controller);
    }
    
    // Test 17: removeItem throws exception after successful equip
    @Test(expected = RuntimeException.class)
    public void testEquipItem_RemoveItemThrowsException_ExceptionPropagated() {
        // Setup
        MockEquipmentStorage throwingStorage = new MockEquipmentStorage() {
            @Override
            public void removeItem(Item item) {
                super.removeItem(item);
                throw new RuntimeException("Test exception from removeItem");
            }
        };
        throwingStorage.addTestItem(item);
        
        List<Storage> storages = new ArrayList<>();
        storages.add(throwingStorage);
        MockEquipmentWithStorage equipment = new MockEquipmentWithStorage(new ArrayList<>(), storages);
        equipment.setEquipItemResult(true);
        
        TestActorForEquipItem testActor = 
            new TestActorForEquipItem("TestActor", "Description", equipment);
        
        // This should propagate the exception
        testActor.equipItem("TestItem", controller);
    }
}