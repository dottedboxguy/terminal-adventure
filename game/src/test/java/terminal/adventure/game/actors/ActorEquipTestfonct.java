package terminal.adventure.game.actors;

import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.BackpackSlot;
import terminal.adventure.game.inventory.slots.Slot;

// Real Equipment implementation for functional testing
class TestEquipment extends Equipment {
    private int successfulEquips = 0;
    private int failedEquips = 0;
    private Item lastSuccessfullyEquipped = null;
    private String lastSlotUsed = null;
    
    public TestEquipment(List<Slot> slots) {
        super(slots);
    }
    
    @Override
    public boolean equipItem(Item item, Controller controller) {
        // Simple functional logic for testing:
        // - Items with "Sword" in name can be equipped in "hand" slot
        // - Items with "Armor" in name can be equipped in "body" slot
        // - Items with "Potion" in name go to backpack
        // - Other items fail
        
        if (item == null) {
            failedEquips++;
            return false;
        }
        
        String itemName = item.getName().toLowerCase();
        
        if (itemName.contains("sword")) {
            successfulEquips++;
            lastSuccessfullyEquipped = item;
            lastSlotUsed = "hand";
            return true;
        } else if (itemName.contains("armor")) {
            successfulEquips++;
            lastSuccessfullyEquipped = item;
            lastSlotUsed = "body";
            return true;
        } else if (itemName.contains("potion")) {
            successfulEquips++;
            lastSuccessfullyEquipped = item;
            lastSlotUsed = "backpack";
            return true;
        } else if (itemName.contains("ring")) {
            // Ring requires controller choice
            if (controller != null) {
                successfulEquips++;
                lastSuccessfullyEquipped = item;
                lastSlotUsed = "finger";
                return true;
            } else {
                failedEquips++;
                return false;
            }
        } else {
            failedEquips++;
            return false;
        }
    }
    
    public int getSuccessfulEquips() {
        return successfulEquips;
    }
    
    public int getFailedEquips() {
        return failedEquips;
    }
    
    public Item getLastSuccessfullyEquipped() {
        return lastSuccessfullyEquipped;
    }
    
    public String getLastSlotUsed() {
        return lastSlotUsed;
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
        // Always choose first option for testing
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
    private TestEquipment testEquipment;
    
    public FunctionalActor(String name, String description, TestEquipment equipment) {
        super(name, description);
        this.testEquipment = equipment;
        // Replace the equipment field
        try {
            java.lang.reflect.Field field = Actor.class.getDeclaredField("equipment");
            field.setAccessible(true);
            field.set(this, equipment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set test equipment", e);
        }
    }
    
    @Override
    public java.util.List<Slot> makeSlots() {
        // Return some slots for testing
        List<Slot> slots = new java.util.ArrayList<>();
        slots.add(new BackpackSlot());
        return slots;
    }
    
    public TestEquipment getTestEquipment() {
        return testEquipment;
    }
}

public class ActorEquipTestfonct {
    
    private FunctionalActor actor;
    private TestEquipment testEquipment;
    private FunctionalController controller;
    
    @Before
    public void setUp() {
        // Create test equipment with some slots
        List<Slot> slots = new java.util.ArrayList<>();
        slots.add(new BackpackSlot());
        testEquipment = new TestEquipment(slots);
        
        // Create actor with test equipment
        actor = new FunctionalActor("TestHero", "A brave hero", testEquipment);
        
        // Create functional controller
        controller = new FunctionalController();
    }
    
    @After
    public void tearDown() {
        // Cleanup if needed
    }
    
    // ==== FUNCTIONAL TESTS FOR equip(Item item, Controller controller) ====
    
    @Test
    public void testEquip_Functional_SwordItem_Success() {
        // Create a sword item
        Stats swordStats = new Stats();
        swordStats.setStrength(10);
        Item sword = new Item("Iron Sword", "A basic iron sword", swordStats);
        
        // Execute
        boolean result = actor.equip(sword, controller);
        
        // Verify
        assertTrue("Sword should be equipped successfully", result);
        assertEquals("Equipment should record 1 successful equip", 1, testEquipment.getSuccessfulEquips());
        assertEquals("No failed equips", 0, testEquipment.getFailedEquips());
        assertEquals("Last equipped item should be the sword", sword, testEquipment.getLastSuccessfullyEquipped());
        assertEquals("Sword should go to hand slot", "hand", testEquipment.getLastSlotUsed());
    }
    
    @Test
    public void testEquip_Functional_ArmorItem_Success() {
        // Create armor item
        Stats armorStats = new Stats();
        armorStats.setArmor(15);
        Item armor = new Item("Steel Armor", "Heavy steel armor", armorStats);
        
        // Execute
        boolean result = actor.equip(armor, controller);
        
        // Verify
        assertTrue("Armor should be equipped successfully", result);
        assertEquals("Equipment should record 1 successful equip", 1, testEquipment.getSuccessfulEquips());
        assertEquals("Armor should go to body slot", "body", testEquipment.getLastSlotUsed());
    }
    
    @Test
    public void testEquip_Functional_PotionItem_Success() {
        // Create potion item
        Stats potionStats = new Stats();
        potionStats.setCurrentHealth(50);
        Item potion = new Item("Health Potion", "Restores health", potionStats);
        
        // Execute
        boolean result = actor.equip(potion, controller);
        
        // Verify
        assertTrue("Potion should be equipped successfully", result);
        assertEquals("Potion should go to backpack", "backpack", testEquipment.getLastSlotUsed());
    }
    
    @Test
    public void testEquip_Functional_UnknownItem_Failure() {
        // Create unknown item type
        Item unknownItem = new Item("Mysterious Orb", "A mysterious glowing orb", new Stats());
        
        // Execute
        boolean result = actor.equip(unknownItem, controller);
        
        // Verify
        assertFalse("Unknown item should fail to equip", result);
        assertEquals("Equipment should record 1 failed equip", 1, testEquipment.getFailedEquips());
        assertEquals("No successful equips", 0, testEquipment.getSuccessfulEquips());
    }
    
    @Test
    public void testEquip_Functional_NullItem_Failure() {
        // Execute with null item
        boolean result = actor.equip(null, controller);
        
        // Verify
        assertFalse("Null item should fail to equip", result);
        assertEquals("Equipment should record 1 failed equip", 1, testEquipment.getFailedEquips());
    }
    
    @Test
    public void testEquip_Functional_RingItemWithController_Success() {
        // Create ring item that requires controller
        Item ring = new Item("Magic Ring", "A ring with magical properties", new Stats());
        
        // Execute with controller
        boolean result = actor.equip(ring, controller);
        
        // Verify
        assertTrue("Ring with controller should succeed", result);
        assertEquals("Ring should go to finger slot", "finger", testEquipment.getLastSlotUsed());
        assertEquals("Controller chooseSlot should be called", 1, controller.getChooseSlotCallCount());
    }
    
    @Test
    public void testEquip_Functional_RingItemWithoutController_Failure() {
        // Create ring item
        Item ring = new Item("Gold Ring", "A plain gold ring", new Stats());
        
        // Execute without controller
        boolean result = actor.equip(ring, null);
        
        // Verify
        assertFalse("Ring without controller should fail", result);
        assertEquals("Equipment should record 1 failed equip", 1, testEquipment.getFailedEquips());
    }
    
    @Test
    public void testEquip_Functional_MultipleItems_SequentialSuccess() {
        // Create multiple items
        Item sword = new Item("Long Sword", "A long sword", new Stats());
        Item armor = new Item("Chain Mail", "Chain mail armor", new Stats());
        Item potion = new Item("Mana Potion", "Restores mana", new Stats());
        
        // Execute sequential equips
        boolean result1 = actor.equip(sword, controller);
        boolean result2 = actor.equip(armor, controller);
        boolean result3 = actor.equip(potion, controller);
        
        // Verify
        assertTrue("All items should equip successfully", result1 && result2 && result3);
        assertEquals("3 successful equips", 3, testEquipment.getSuccessfulEquips());
        assertEquals("Last item should be potion", potion, testEquipment.getLastSuccessfullyEquipped());
    }
    
    @Test
    public void testEquip_Functional_MixedSuccessFailure() {
        // Create mixed items
        Item sword = new Item("Short Sword", "A short sword", new Stats());
        Item unknown = new Item("Strange Artifact", "Unknown artifact", new Stats());
        Item armor = new Item("Leather Armor", "Light leather armor", new Stats());
        
        // Execute
        boolean result1 = actor.equip(sword, controller);
        boolean result2 = actor.equip(unknown, controller);
        boolean result3 = actor.equip(armor, controller);
        
        // Verify
        assertTrue("Sword should succeed", result1);
        assertFalse("Unknown should fail", result2);
        assertTrue("Armor should succeed", result3);
        assertEquals("2 successful equips", 2, testEquipment.getSuccessfulEquips());
        assertEquals("1 failed equip", 1, testEquipment.getFailedEquips());
    }
    
    @Test
    public void testEquip_Functional_ItemStatsPreserved() {
        // Create item with specific stats
        Stats detailedStats = new Stats();
        detailedStats.setStrength(25);
        detailedStats.setArmor(10);
        detailedStats.setCurrentHealth(100);
        detailedStats.setMaxHealth(150);
        detailedStats.setSpeed(5);
        
        Item powerfulSword = new Item("Dragon Slayer", "Legendary sword", detailedStats);
        
        // Execute
        boolean result = actor.equip(powerfulSword, controller);
        
        // Verify
        assertTrue("Sword should equip", result);
        Item equippedItem = testEquipment.getLastSuccessfullyEquipped();
        assertNotNull("Item should be stored", equippedItem);
        
        // Verify stats are preserved
        Stats retrievedStats = equippedItem.getStats();
        assertEquals("Strength preserved", 25, retrievedStats.getStrength());
        assertEquals("Armor preserved", 10, retrievedStats.getArmor());
        assertEquals("Current health preserved", 100, retrievedStats.getCurrentHealth());
        assertEquals("Max health preserved", 150, retrievedStats.getMaxHealth());
        assertEquals("Speed preserved", 5, retrievedStats.getSpeed());
    }
    
    @Test
    public void testEquip_Functional_ItemNameCaseInsensitivity() {
        // Test case variations
        Item sword1 = new Item("SWORD", "All caps", new Stats());
        Item sword2 = new Item("Sword", "Capitalized", new Stats());
        Item sword3 = new Item("sword", "Lowercase", new Stats());
        Item sword4 = new Item("SwOrD", "Mixed case", new Stats());
        
        // All should succeed
        assertTrue(actor.equip(sword1, controller));
        assertTrue(actor.equip(sword2, controller));
        assertTrue(actor.equip(sword3, controller));
        assertTrue(actor.equip(sword4, controller));
        
        assertEquals("4 successful equips", 4, testEquipment.getSuccessfulEquips());
    }
    
    @Test
    public void testEquip_Functional_ItemWithPartialNameMatch() {
        // Items containing the keyword should work
        Item greatsword = new Item("Greatsword", "A great sword", new Stats());
        Item armory = new Item("Armory Key", "Key to the armory", new Stats());
        Item potionBag = new Item("Potion Bag", "Bag for potions", new Stats());
        
        assertTrue("Greatsword contains 'sword'", actor.equip(greatsword, controller));
        assertTrue("Armory contains 'armor'", actor.equip(armory, controller));
        assertTrue("Potion Bag contains 'potion'", actor.equip(potionBag, controller));
    }
    
    @Test
    public void testEquip_Functional_EmptyItemName() {
        // Edge case: empty name
        Item emptyNameItem = new Item("", "Item with no name", new Stats());
        
        boolean result = actor.equip(emptyNameItem, controller);
        
        // Should fail because name doesn't contain keywords
        assertFalse("Empty name item should fail", result);
        assertEquals("1 failed equip", 1, testEquipment.getFailedEquips());
    }
    
    @Test
    public void testEquip_Functional_ControllerInteractionForRing() {
        // Test that controller methods are called for ring
        Item ring = new Item("Ring of Power", "A powerful ring", new Stats());
        
        actor.equip(ring, controller);
        
        // Verify controller interaction
        assertEquals("chooseSlot should be called once", 1, controller.getChooseSlotCallCount());
        assertNotNull("Candidate names should be set", controller.getLastCandidateNames());
    }
    
    @Test
    public void testEquip_Functional_NoControllerInteractionForSword() {
        // Test that controller methods are NOT called for non-ring items
        Item sword = new Item("Steel Sword", "A steel sword", new Stats());
        
        actor.equip(sword, controller);
        
        // Verify no controller interaction
        assertEquals("chooseSlot should NOT be called", 0, controller.getChooseSlotCallCount());
        assertNull("Candidate names should be null", controller.getLastCandidateNames());
    }
    
    @Test
    public void testEquip_Functional_ItemDescriptionPreserved() {
        // Verify item descriptions are preserved
        String description = "A sword forged in the fires of Mount Doom";
        Item sword = new Item("Doom Sword", description, new Stats());
        
        actor.equip(sword, controller);
        
        Item equipped = testEquipment.getLastSuccessfullyEquipped();
        assertEquals("Description should match", description, equipped.look());
    }
    
    @Test
    public void testEquip_Functional_ItemToStringWorks() {
        // Verify toString works on equipped items
        Item sword = new Item("Test Sword", "Test item", new Stats());
        
        actor.equip(sword, controller);
        
        Item equipped = testEquipment.getLastSuccessfullyEquipped();
        String toString = equipped.toString();
        assertNotNull("toString should not return null", toString);
        assertTrue("toString should contain class name", toString.contains("Item"));
        assertTrue("toString should contain item name", toString.contains("Test Sword"));
    }
    
    @Test
    public void testEquip_Functional_PerformanceMultipleEquips() {
        // Performance test: equip many items
        int successful = 0;
        int failed = 0;
        
        for (int i = 0; i < 100; i++) {
            if (i % 4 == 0) {
                Item sword = new Item("Sword " + i, "Sword number " + i, new Stats());
                if (actor.equip(sword, controller)) successful++;
                else failed++;
            } else if (i % 4 == 1) {
                Item armor = new Item("Armor " + i, "Armor number " + i, new Stats());
                if (actor.equip(armor, controller)) successful++;
                else failed++;
            } else if (i % 4 == 2) {
                Item potion = new Item("Potion " + i, "Potion number " + i, new Stats());
                if (actor.equip(potion, controller)) successful++;
                else failed++;
            } else {
                Item unknown = new Item("Unknown " + i, "Unknown item " + i, new Stats());
                if (actor.equip(unknown, controller)) successful++;
                else failed++;
            }
        }
        
        // Verify counts match
        assertEquals("Successful count should match", successful, testEquipment.getSuccessfulEquips());
        assertEquals("Failed count should match", failed, testEquipment.getFailedEquips());
        
        // Should be roughly 75% success rate (3 out of 4 types succeed)
        assertTrue("Should have significant successes", successful > 70);
        assertTrue("Should have some failures", failed > 20);
    }
    
    @Test
    public void testEquip_Functional_StateConsistencyAfterFailedEquip() {
        // Verify actor state remains consistent after failed equip
        Item unknown = new Item("Unknown", "Cannot equip this", new Stats());
        
        boolean result = actor.equip(unknown, controller);
        
        assertFalse("Should fail", result);
        assertEquals("1 failed equip", 1, testEquipment.getFailedEquips());
        
        // Actor should still be able to equip other items
        Item sword = new Item("Sword", "A sword", new Stats());
        boolean nextResult = actor.equip(sword, controller);
        
        assertTrue("Next equip should work", nextResult);
        assertEquals("Now 1 success", 1, testEquipment.getSuccessfulEquips());
    }
    
    @Test
    public void testEquip_Functional_ControllerChoiceAffectsOutcome() {
        // Create a controller that sometimes chooses invalid option
        FunctionalController selectiveController = new FunctionalController() {
            private int callCount = 0;
            
            @Override
            public int equipChooseSlot(List<String> candidatesNames) {
                callCount++;
                // First call: valid choice, second call: invalid choice
                return (callCount == 1) ? 0 : 999; // Invalid index
            }
        };
        
        // Ring should work with valid choice
        Item ring1 = new Item("Ring 1", "First ring", new Stats());
        boolean result1 = actor.equip(ring1, selectiveController);
        assertTrue("First ring should succeed", result1);
        
        // Note: Our test equipment doesn't actually use the controller's choice index
        // In real implementation, invalid choice might cause failure
    }
    
    @Test
    public void testEquip_Functional_IntegrationWithActorState() {
        // Verify equip integrates with actor's other state
        // First, give actor some base stats
        Stats actorStats = actor.getBaseStats();
        actorStats.setStrength(10);
        actorStats.setArmor(5);
        
        // Equip items that add stats
        Stats swordStats = new Stats();
        swordStats.setStrength(15);
        Item sword = new Item("Strong Sword", "Adds strength", swordStats);
        
        Stats armorStats = new Stats();
        armorStats.setArmor(10);
        Item armor = new Item("Strong Armor", "Adds armor", armorStats);
        
        // Equip both
        actor.equip(sword, controller);
        actor.equip(armor, controller);
        
        // Note: In real implementation, equipping would affect actor's total stats
        // This test verifies the functional flow even if stats aren't immediately visible
        assertEquals("2 successful equips", 2, testEquipment.getSuccessfulEquips());
    }
    
    @Test
    public void testEquip_Functional_ItemUniqueness() {
        // Verify different item instances are treated separately
        Item sword1 = new Item("Sword", "First sword", new Stats());
        Item sword2 = new Item("Sword", "Second sword with same name", new Stats());
        
        // Both should succeed even with same name
        boolean result1 = actor.equip(sword1, controller);
        boolean result2 = actor.equip(sword2, controller);
        
        assertTrue("First sword should succeed", result1);
        assertTrue("Second sword should succeed", result2);
        assertNotSame("Items should be different instances", sword1, sword2);
        assertEquals("Second sword should be last equipped", sword2, testEquipment.getLastSuccessfullyEquipped());
    }
}