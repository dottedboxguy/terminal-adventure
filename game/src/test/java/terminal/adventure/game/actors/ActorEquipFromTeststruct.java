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

import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

// Mock Storage implementing the updated interface
class MockStorage implements Storage {
    private List<Item> items = new ArrayList<>();
    private boolean containsItemResult = false;
    private int removeItemCallCount = 0;
    private Item lastRemovedItem = null;
    private int containsCallCount = 0;
    private Item lastCheckedItem = null;
    private int addItemCallCount = 0;
    private Item lastAddedItem = null;
    private int clearCallCount = 0;
    private int dumpCallCount = 0;
    private Storage lastDumpTarget = null;
    private int searchItemsCallCount = 0;
    private String lastSearchedName = null;
    
    public void setContainsItemResult(boolean result) {
        this.containsItemResult = result;
    }
    
    @Override
    public boolean contains(Item item) {
        containsCallCount++;
        lastCheckedItem = item;
        return containsItemResult;
    }
    
    @Override
    public void removeItem(Item item) {
        removeItemCallCount++;
        lastRemovedItem = item;
        items.remove(item);
    }
    
    @Override
    public void addItem(Item item) {
        addItemCallCount++;
        lastAddedItem = item;
        items.add(item);
    }
    
    @Override
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }
    
    @Override
    public List<Item> searchItems(String itemName) {
        searchItemsCallCount++;
        lastSearchedName = itemName;
        return new ArrayList<>();
    }
    
    @Override
    public void dump(Storage target) {
        dumpCallCount++;
        lastDumpTarget = target;
    }
    
    @Override
    public void clear() {
        clearCallCount++;
        items.clear();
    }
    
    public int getRemoveItemCallCount() {
        return removeItemCallCount;
    }
    
    public Item getLastRemovedItem() {
        return lastRemovedItem;
    }
    
    public int getContainsCallCount() {
        return containsCallCount;
    }
    
    public Item getLastCheckedItem() {
        return lastCheckedItem;
    }
    
    public int getAddItemCallCount() {
        return addItemCallCount;
    }
    
    public Item getLastAddedItem() {
        return lastAddedItem;
    }
    
    public int getClearCallCount() {
        return clearCallCount;
    }
    
    public int getDumpCallCount() {
        return dumpCallCount;
    }
    
    public Storage getLastDumpTarget() {
        return lastDumpTarget;
    }
    
    public int getSearchItemsCallCount() {
        return searchItemsCallCount;
    }
    
    public String getLastSearchedName() {
        return lastSearchedName;
    }
    
    public void addTestItem(Item item) {
        items.add(item);
        containsItemResult = true;
    }
}

// Mock Equipment for testing
class MockEquipment extends Equipment {
    private boolean equipItemResult;
    private Item lastEquippedItem;
    private Controller lastUsedController;
    private int equipCallCount = 0;
    
    public MockEquipment(List<Slot> slots) {
        super(slots);
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

// Mock Controller for testing
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

// Concrete Actor class for testing with accessible equipment
class TestActorWithMocks extends Actor {
    private MockEquipment mockEquipment;
    
    public TestActorWithMocks(String name, String description, MockEquipment equipment) {
        super(name, description);
        this.mockEquipment = equipment;
        // Override the equipment field using reflection
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
        return new java.util.ArrayList<>();
    }
    
    public MockEquipment getMockEquipment() {
        return mockEquipment;
    }
}

public class ActorEquipFromTeststruct {
    
    private TestActorWithMocks actor;
    private MockEquipment mockEquipment;
    private MockController controller;
    private MockStorage storage;
    private Item item;
    
    @Before
    public void setUp() {
        // Create mocks
        mockEquipment = new MockEquipment(new java.util.ArrayList<>());
        storage = new MockStorage();
        
        // Create actor with mock equipment
        actor = new TestActorWithMocks("TestActor", "Description", mockEquipment);
        
        // Create controller and item
        controller = new MockController();
        item = new Item("TestItem", "A test item", new Stats());
    }
    
    @After
    public void tearDown() {
        // Cleanup if needed
    }
    
    // ==== STRUCTURAL TESTS FOR equipFrom(Item item, Controller controller, Storage source) ====
    
    // Test 1: Storage contains item = true, Equipment.equipItem = true → Returns true, Item removed
    @Test
    public void testEquipFrom_StorageContainsTrue_EquipItemTrue_ReturnsTrue_ItemRemoved() {
        // Setup - Add item to storage and set up mocks
        storage.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Execution
        boolean result = actor.equipFrom(item, controller, storage);
        
        // Verifications
        assertTrue("Should return true when item is in storage and equip succeeds", result);
        assertEquals("Storage.contains should be called once", 1, storage.getContainsCallCount());
        assertEquals("Equipment.equipItem should be called once", 1, mockEquipment.getEquipCallCount());
        assertEquals("Storage.removeItem should be called once", 1, storage.getRemoveItemCallCount());
        assertEquals("Correct item should be checked in storage", item, storage.getLastCheckedItem());
        assertEquals("Correct item should be passed to equipment", item, mockEquipment.getLastEquippedItem());
        assertEquals("Correct item should be removed from storage", item, storage.getLastRemovedItem());
        assertEquals("Correct controller should be passed", controller, mockEquipment.getLastUsedController());
        assertFalse("Item should no longer be in storage", storage.getItems().contains(item));
    }
    
    // Test 2: Storage contains item = true, Equipment.equipItem = false → Returns false, Item NOT removed
    @Test
    public void testEquipFrom_StorageContainsTrue_EquipItemFalse_ReturnsFalse_ItemNotRemoved() {
        // Setup
        storage.addTestItem(item);
        mockEquipment.setEquipItemResult(false);
        
        // Execution
        boolean result = actor.equipFrom(item, controller, storage);
        
        // Verifications
        assertFalse("Should return false when equip fails", result);
        assertEquals("Storage.contains should be called once", 1, storage.getContainsCallCount());
        assertEquals("Equipment.equipItem should be called once", 1, mockEquipment.getEquipCallCount());
        assertEquals("Storage.removeItem should NOT be called", 0, storage.getRemoveItemCallCount());
        assertEquals("Correct item should be checked", item, storage.getLastCheckedItem());
        assertEquals("Correct item should be passed to equipment", item, mockEquipment.getLastEquippedItem());
        assertNull("No item should be removed", storage.getLastRemovedItem());
        assertTrue("Item should still be in storage", storage.getItems().contains(item));
    }
    
    // Test 3: Storage contains item = false → Returns false, Short-circuit evaluation
    @Test
    public void testEquipFrom_StorageContainsFalse_ReturnsFalse_ShortCircuit() {
        // Setup - Don't add item to storage
        mockEquipment.setEquipItemResult(true); // This should not be called
        
        // Execution
        boolean result = actor.equipFrom(item, controller, storage);
        
        // Verifications
        assertFalse("Should return false when item not in storage", result);
        assertEquals("Storage.contains should be called once", 1, storage.getContainsCallCount());
        assertEquals("Equipment.equipItem should NOT be called (short-circuit)", 0, mockEquipment.getEquipCallCount());
        assertEquals("Storage.removeItem should NOT be called", 0, storage.getRemoveItemCallCount());
        assertEquals("Correct item should be checked", item, storage.getLastCheckedItem());
        assertNull("No item should be passed to equipment", mockEquipment.getLastEquippedItem());
        assertNull("No item should be removed", storage.getLastRemovedItem());
    }
    
    // Test 4: Null item parameter
    @Test
    public void testEquipFrom_NullItemParameter() {
        // Setup
        storage.setContainsItemResult(true);
        mockEquipment.setEquipItemResult(true);
        
        // Execution with null item
        boolean result = actor.equipFrom(null, controller, storage);
        
        // Verifications
        // The behavior depends on Storage.contains(null) implementation
        assertEquals("Storage.contains should be called", 1, storage.getContainsCallCount());
        assertNull("Null item should be checked", storage.getLastCheckedItem());
        
        // If contains returns false, equipItem won't be called
        // If contains returns true, equipItem will be called with null
    }
    
    // Test 5: Null controller parameter
    @Test
    public void testEquipFrom_NullControllerParameter() {
        // Setup
        storage.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Execution with null controller
        boolean result = actor.equipFrom(item, null, storage);
        
        // Verifications
        assertEquals("Storage.contains should be called", 1, storage.getContainsCallCount());
        assertEquals("Equipment.equipItem should be called", 1, mockEquipment.getEquipCallCount());
        assertNull("Null controller should be passed to equipment", mockEquipment.getLastUsedController());
        assertEquals("Storage.removeItem should be called", 1, storage.getRemoveItemCallCount());
        assertFalse("Item should be removed from storage", storage.getItems().contains(item));
    }
    
    // Test 6: Null storage parameter
    @Test(expected = NullPointerException.class)
    public void testEquipFrom_NullStorageParameter_NullPointerException() {
        // Execution with null storage
        // This will cause NullPointerException when calling source.contains(item)
        actor.equipFrom(item, controller, null);
    }
    
    // Test 7: All parameters null
    @Test(expected = NullPointerException.class)
    public void testEquipFrom_AllParametersNull_NullPointerException() {
        // Execution with all null
        actor.equipFrom(null, null, null);
    }
    
    // Test 8: Multiple successful equipFrom calls
    @Test
    public void testEquipFrom_MultipleSuccessfulCalls() {
        // Setup
        storage.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        Item item2 = new Item("Item2", "Second item", new Stats());
        MockStorage storage2 = new MockStorage();
        storage2.addTestItem(item2);
        
        MockController controller2 = new MockController();
        
        // Multiple calls
        boolean result1 = actor.equipFrom(item, controller, storage);
        boolean result2 = actor.equipFrom(item2, controller2, storage2);
        
        // Verifications
        assertTrue("First call should succeed", result1);
        assertTrue("Second call should succeed", result2);
        assertEquals("Storage1 contains called once", 1, storage.getContainsCallCount());
        assertEquals("Storage2 contains called once", 1, storage2.getContainsCallCount());
        assertEquals("Equipment.equipItem called twice", 2, mockEquipment.getEquipCallCount());
        assertEquals("Storage1 removeItem called once", 1, storage.getRemoveItemCallCount());
        assertEquals("Storage2 removeItem called once", 1, storage2.getRemoveItemCallCount());
        assertEquals("Last item should be item2", item2, mockEquipment.getLastEquippedItem());
        assertEquals("Last controller should be controller2", controller2, mockEquipment.getLastUsedController());
        assertFalse("Item1 should be removed from storage1", storage.getItems().contains(item));
        assertFalse("Item2 should be removed from storage2", storage2.getItems().contains(item2));
    }
    
    // Test 9: Storage.contains throws exception
    @Test(expected = RuntimeException.class)
    public void testEquipFrom_StorageContainsThrowsException_ExceptionPropagated() {
        // Create storage that throws exception
        MockStorage throwingStorage = new MockStorage() {
            @Override
            public boolean contains(Item item) {
                super.contains(item);
                throw new RuntimeException("Test exception from storage.contains");
            }
        };
        
        // This should propagate the exception
        actor.equipFrom(item, controller, throwingStorage);
    }
    
    // Test 10: Equipment.equipItem throws exception after successful contains check
    @Test(expected = RuntimeException.class)
    public void testEquipFrom_EquipItemThrowsException_ExceptionPropagated() {
        // Setup
        storage.addTestItem(item);
        
        // Create equipment that throws exception
        MockEquipment throwingEquipment = new MockEquipment(new java.util.ArrayList<>()) {
            @Override
            public boolean equipItem(Item item, Controller controller) {
                super.equipItem(item, controller);
                throw new RuntimeException("Test exception from equipment.equipItem");
            }
        };
        
        TestActorWithMocks actorWithThrowingEquipment = 
            new TestActorWithMocks("TestActor", "Description", throwingEquipment);
        
        // This should propagate the exception
        actorWithThrowingEquipment.equipFrom(item, controller, storage);
    }
    
    // Test 11: Storage.removeItem throws exception after successful equip
    @Test(expected = RuntimeException.class)
    public void testEquipFrom_RemoveItemThrowsException_ExceptionPropagated() {
        // Setup
        storage.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Create storage that throws exception on remove
        MockStorage throwingStorage = new MockStorage() {
            @Override
            public void removeItem(Item item) {
                super.removeItem(item);
                throw new RuntimeException("Test exception from storage.removeItem");
            }
        };
        throwingStorage.addTestItem(item);
        
        // This should propagate the exception
        actor.equipFrom(item, controller, throwingStorage);
    }
    
    // Test 12: Verify order of operations
    @Test
    public void testEquipFrom_OrderOfOperations_Correct() {
        // Setup to verify order
        storage.addTestItem(item);
        mockEquipment.setEquipItemResult(true);
        
        // Track operation order
        final StringBuilder order = new StringBuilder();
        
        MockStorage orderedStorage = new MockStorage() {
            @Override
            public boolean contains(Item item) {
                order.append("1-contains ");
                return super.contains(item);
            }
            
            @Override
            public void removeItem(Item item) {
                order.append("3-remove ");
                super.removeItem(item);
            }
        };
        orderedStorage.addTestItem(item);
        
        MockEquipment orderedEquipment = new MockEquipment(new java.util.ArrayList<>()) {
            @Override
            public boolean equipItem(Item item, Controller controller) {
                order.append("2-equip ");
                return super.equipItem(item, controller);
            }
        };
        orderedEquipment.setEquipItemResult(true);
        
        TestActorWithMocks orderedActor = 
            new TestActorWithMocks("TestActor", "Description", orderedEquipment);
        
        // Execute
        orderedActor.equipFrom(item, controller, orderedStorage);
        
        // Verify order: 1. contains, 2. equip, 3. remove
        assertEquals("Operations should be in correct order", "1-contains 2-equip 3-remove ", order.toString());
    }
    
    // Test 13: Different storage instances with same item
    @Test
    public void testEquipFrom_DifferentStorageInstances() {
        mockEquipment.setEquipItemResult(true);
        
        MockStorage storage1 = new MockStorage();
        storage1.addTestItem(item);
        
        MockStorage storage2 = new MockStorage();
        storage2.addTestItem(item);
        
        // Item is the same instance
        actor.equipFrom(item, controller, storage1);
        actor.equipFrom(item, controller, storage2);
        
        // Both should succeed
        assertEquals("Equipment.equipItem called twice", 2, mockEquipment.getEquipCallCount());
        assertEquals("Storage1 removeItem called", 1, storage1.getRemoveItemCallCount());
        assertEquals("Storage2 removeItem called", 1, storage2.getRemoveItemCallCount());
        assertFalse("Item removed from storage1", storage1.getItems().contains(item));
        assertFalse("Item removed from storage2", storage2.getItems().contains(item));
    }
    
    // Test 14: Storage contains varies between calls
    @Test
    public void testEquipFrom_StorageContainsVaries() {
        mockEquipment.setEquipItemResult(true);
        
        // First call: storage has item
        storage.addTestItem(item);
        boolean result1 = actor.equipFrom(item, controller, storage);
        
        // Reset storage for second call (item was removed)
        MockStorage storage2 = new MockStorage();
        // Don't add item - storage won't contain it
        
        // Second call: storage doesn't have item
        boolean result2 = actor.equipFrom(item, controller, storage2);
        
        // Verifications
        assertTrue("First call should succeed", result1);
        assertFalse("Second call should fail", result2);
        assertEquals("Storage1 contains called once", 1, storage.getContainsCallCount());
        assertEquals("Storage2 contains called once", 1, storage2.getContainsCallCount());
        assertEquals("Equipment.equipItem called once (second call short-circuits)", 1, mockEquipment.getEquipCallCount());
        assertEquals("Storage1 removeItem called once (only for first call)", 1, storage.getRemoveItemCallCount());
        assertEquals("Storage2 removeItem NOT called", 0, storage2.getRemoveItemCallCount());
    }
    
    // Test 15: Equipment returns different equipItem results
    @Test
    public void testEquipFrom_EquipItemResultVaries() {
        storage.addTestItem(item);
        
        // First call: equip succeeds
        mockEquipment.setEquipItemResult(true);
        boolean result1 = actor.equipFrom(item, controller, storage);
        
        // Reset for second call with new storage and item
        MockStorage storage2 = new MockStorage();
        Item item2 = new Item("Item2", "Second item", new Stats());
        storage2.addTestItem(item2);
        
        // Second call: equip fails
        mockEquipment.setEquipItemResult(false);
        boolean result2 = actor.equipFrom(item2, controller, storage2);
        
        // Verifications
        assertTrue("First call should succeed", result1);
        assertFalse("Second call should fail", result2);
        assertEquals("Storage1 removeItem called", 1, storage.getRemoveItemCallCount());
        assertEquals("Storage2 removeItem NOT called", 0, storage2.getRemoveItemCallCount());
        assertFalse("Item1 removed from storage1", storage.getItems().contains(item));
        assertTrue("Item2 still in storage2", storage2.getItems().contains(item2));
    }
    
    // Test 16: Item actually removed from storage list
    @Test
    public void testEquipFrom_ItemActuallyRemovedFromStorage() {
        // Setup - Add multiple items to storage
        storage.addTestItem(item);
        Item item2 = new Item("Item2", "Another item", new Stats());
        storage.addTestItem(item2);
        Item item3 = new Item("Item3", "Third item", new Stats());
        storage.addTestItem(item3);
        
        mockEquipment.setEquipItemResult(true);
        
        // Initial state
        assertEquals("Storage should have 3 items initially", 3, storage.getItems().size());
        
        // Execute - equip item2
        boolean result = actor.equipFrom(item2, controller, storage);
        
        // Verifications
        assertTrue("Equip should succeed", result);
        assertEquals("Storage should have 2 items after removal", 2, storage.getItems().size());
        assertTrue("Item1 should still be in storage", storage.getItems().contains(item));
        assertFalse("Item2 should be removed", storage.getItems().contains(item2));
        assertTrue("Item3 should still be in storage", storage.getItems().contains(item3));
    }
    
    // Test 17: Storage.removeItem called even if item not in items list but contains returns true
    @Test
    public void testEquipFrom_RemoveItemCalledEvenIfContainsBasedOnMock() {
        // Setup - Mock returns true for contains but item not actually in list
        storage.setContainsItemResult(true); // Mock returns true
        mockEquipment.setEquipItemResult(true);
        
        // Note: The item is NOT added to storage via addTestItem()
        // So getItems() would return empty list
        
        // Execution
        boolean result = actor.equipFrom(item, controller, storage);
        
        // Verifications
        assertTrue("Should return true based on mock", result);
        assertEquals("Storage.removeItem should still be called", 1, storage.getRemoveItemCallCount());
        assertEquals("Item should be passed to remove", item, storage.getLastRemovedItem());
    }
}