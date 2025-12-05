package terminal.adventure.game.actors;

import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;

// Mock Controller for testing
class MockController extends Controller {
    private Actor boundActor = null;
    private int unbindCallCount = 0;
    private int bindCallCount = 0;
    private int dieCallCount = 0;
    
    public MockController() {
        super(null);
    }
    
    @Override
    public void bindActor(Actor actor) {
        this.boundActor = actor;
        bindCallCount++;
    }
    
    @Override
    public void unbindActor() {
        this.boundActor = null;
        unbindCallCount++;
    }
    
    @Override
    public Actor getActor() {
        return boundActor;
    }
    
    public int getUnbindCallCount() {
        return unbindCallCount;
    }
    
    public int getBindCallCount() {
        return bindCallCount;
    }
    
    public int getDieCallCount() {
        return dieCallCount;
    }
    
    @Override
    public void die() {
        dieCallCount++;
    }

    @Override
    public int equipChooseSlot(List<String> candidatesNames) {
        return 1;
    }

    @Override
    protected void play() {
    }

    @Override
    public void takeAttackReport(Stats report) {
    }
}

// Concrete Actor class for testing
class TestActor extends Actor {
    public TestActor(String name, String description) {
        super(name, description);
    }
    
    @Override
    public java.util.List<terminal.adventure.game.inventory.slots.Slot> makeSlots() {
        return new java.util.ArrayList<>();
    }
}

public class ActorClearControllerTestfonct {
    
    private TestActor actor;
    private MockController controller1;
    private MockController controller2;
    private MockController controller3;
    
    @Before
    public void setUp() {
        actor = new TestActor("TestActor", "Description");
        controller1 = new MockController();
        controller2 = new MockController();
        controller3 = new MockController();
    }
    
    @After
    public void tearDown() {
        // Cleanup if needed
    }
    
    // ==== FUNCTIONAL TESTS FOR clearController() ====
    
    @Test
    public void testClearController_NoController_NothingHappens() {
        // Precondition: Actor has no controller
        assertNull("Actor should have no controller initially", actor.getController());
        assertEquals("No unbind calls initially", 0, controller1.getUnbindCallCount());
        
        // Execution
        actor.clearController();
        
        // Verification
        assertNull("Actor should still have no controller", actor.getController());
        assertEquals("No unbind calls should be made", 0, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_SingleController_ControllerClearedAndUnbound() {
        // Preparation: Set a controller
        actor.setController(controller1);
        
        // Precondition verification
        assertEquals("Actor should have controller1", controller1, actor.getController());
        assertEquals("Controller1 should be bound to actor", actor, controller1.getActor());
        assertEquals("No unbind calls before clear", 0, controller1.getUnbindCallCount());
        
        // Execution
        actor.clearController();
        
        // Verification
        assertNull("Actor should no longer have a controller", actor.getController());
        assertNull("Controller1 should be unbound", controller1.getActor());
        assertEquals("Controller1.unbindActor should be called once", 1, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_MultipleControllersInSequence() {
        // Test clearing different controllers in sequence
        actor.setController(controller1);
        actor.clearController();
        
        actor.setController(controller2);
        actor.clearController();
        
        actor.setController(controller3);
        actor.clearController();
        
        // Verification
        assertNull("Actor should have no controller", actor.getController());
        assertEquals("Controller1 unbind called once", 1, controller1.getUnbindCallCount());
        assertEquals("Controller2 unbind called once", 1, controller2.getUnbindCallCount());
        assertEquals("Controller3 unbind called once", 1, controller3.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_AfterMultipleSetControllerCalls() {
        // Set and clear multiple times
        actor.setController(controller1);
        actor.setController(controller2); // This should clear controller1
        actor.clearController(); // This should clear controller2
        
        // Verification
        assertNull("Actor should have no controller", actor.getController());
        assertEquals("Controller1 unbind called once (by setController)", 1, controller1.getUnbindCallCount());
        assertEquals("Controller2 unbind called once (by clearController)", 1, controller2.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_MultipleCallsOnSameController() {
        // Set controller
        actor.setController(controller1);
        
        // Clear multiple times
        actor.clearController();
        actor.clearController(); // Should do nothing
        actor.clearController(); // Should do nothing
        
        // Verification
        assertNull("Actor should have no controller", actor.getController());
        assertEquals("unbindActor should be called exactly once", 1, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_ControllerStatePreservedInTempVariable() {
        // This test verifies that the temp variable preserves the controller reference
        actor.setController(controller1);
        
        // The implementation uses temp variable to avoid null pointer
        // Controller temp = this.controller;
        // this.controller = null;
        // temp.unbindActor();
        
        actor.clearController();
        
        // If temp wasn't used, we might call unbindActor on null
        assertNull(actor.getController());
        assertEquals(1, controller1.getUnbindCallCount());
        assertNull("Controller should be unbound", controller1.getActor());
    }
    
    @Test
    public void testClearController_WithControllerBoundToOtherActor() {
        // Create another actor bound to same controller
        TestActor otherActor = new TestActor("OtherActor", "Description");
        actor.setController(controller1);
        otherActor.setController(controller1); // Both actors use same controller
        
        // Clear controller from first actor
        actor.clearController();
        
        // Verification
        assertNull("Actor should have no controller", actor.getController());
        assertEquals("Controller1 unbind called once", 1, controller1.getUnbindCallCount());
        assertEquals("Other actor should still have controller1", controller1, otherActor.getController());
    }
    
    @Test
    public void testClearController_ThenSetNewController() {
        // Test sequence: set, clear, set new
        actor.setController(controller1);
        actor.clearController();
        
        MockController newController = new MockController();
        actor.setController(newController);
        
        // Verification
        assertEquals("Actor should have new controller", newController, actor.getController());
        assertEquals("New controller should be bound to actor", actor, newController.getActor());
        assertEquals("Old controller unbind called once", 1, controller1.getUnbindCallCount());
        assertEquals("New controller bind called once", 1, newController.getBindCallCount());
    }
    
    @Test
    public void testClearController_AfterActorDies() {
        // Simulate actor death
        actor.setController(controller1);
        actor.getBaseStats().setCurrentHealth(0);
        assertTrue("Actor should be dead", actor.isDead());
        
        // Clear controller (should still work)
        actor.clearController();
        
        // Verification
        assertNull("Dead actor should have no controller", actor.getController());
        assertEquals("Controller unbind should be called", 1, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_WhenControllerAlreadyUnboundExternally() {
        // Set controller
        actor.setController(controller1);
        
        // Manually unbind controller (simulating external action)
        controller1.unbindActor();
        assertNull("Controller should be unbound", controller1.getActor());
        assertEquals("Manual unbind counted", 1, controller1.getUnbindCallCount());
        
        // Now clear controller from actor
        actor.clearController();
        
        // Verification
        assertNull("Actor should have no controller", actor.getController());
        assertEquals("unbindActor should be called again", 2, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_IntegrationWithDieMethod() {
        // Set controller
        actor.setController(controller1);
        
        // Simulate actor death (which should call die() method)
        // In the actual die() method, clearController might be called
        actor.die();
        
        // Check if die method affects controller
        // Note: This depends on actual implementation of die()
        assertEquals("Controller die should be called", 1, controller1.getDieCallCount());
    }
    
    @Test
    public void testClearController_PerformanceMultipleCalls() {
        // Performance test: multiple set/clear operations
        for (int i = 0; i < 100; i++) {
            actor.setController(controller1);
            actor.clearController();
        }
        
        // Verification
        assertNull("Actor should have no controller after loop", actor.getController());
        assertEquals("Controller unbind called 100 times", 100, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_StateConsistencyAfterException() {
        // Test that actor state remains consistent even if unbindActor throws exception
        MockController throwingController = new MockController() {
            @Override
            public void unbindActor() {
                super.unbindActor();
                throw new RuntimeException("Test exception");
            }
        };
        
        actor.setController(throwingController);
        
        try {
            actor.clearController();
            fail("Should have thrown exception");
        } catch (RuntimeException e) {
            // Expected
            assertEquals("Test exception", e.getMessage());
        }
        
        // Even with exception, actor's controller should be null
        assertNull("Actor controller should be null even after exception", actor.getController());
    }
    
    @Test
    public void testClearController_ThreadSafetyCheck() throws InterruptedException {
        // Basic thread safety check (not comprehensive)
        actor.setController(controller1);
        
        Thread t1 = new Thread(() -> actor.clearController());
        Thread t2 = new Thread(() -> actor.clearController());
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        // At least one unbind should be called, at most two
        assertTrue("unbind should be called at least once", controller1.getUnbindCallCount() >= 1);
        assertNull("Actor should have no controller", actor.getController());
    }
    
    @Test
    public void testClearController_WithNullControllerInBetween() {
        // Test scenario: set, clear, set null, clear
        actor.setController(controller1);
        actor.clearController(); // Clears controller1
        
        actor.setController(null); // Explicitly set to null
        actor.clearController(); // Should do nothing
        
        // Verification
        assertNull("Actor should have no controller", actor.getController());
        assertEquals("Controller1 unbind called once", 1, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testClearController_VerifyControllerChainNotBroken() {
        // Test that clearing doesn't break controller-actor chain
        actor.setController(controller1);
        
        // Verify initial chain
        assertEquals(actor, controller1.getActor());
        assertEquals(controller1, actor.getController());
        
        // Clear
        actor.clearController();
        
        // Verify chain is broken
        assertNull(controller1.getActor());
        assertNull(actor.getController());
        
        // Set again
        actor.setController(controller1);
        
        // Verify chain is restored
        assertEquals(actor, controller1.getActor());
        assertEquals(controller1, actor.getController());
    }
    
    @Test
    public void testClearController_MemoryLeakPrevention() {
        // Test that references are properly cleared
        actor.setController(controller1);
        
        // Keep reference to controller
        Controller controllerRef = actor.getController();
        
        // Clear controller
        actor.clearController();
        
        // Actor should not reference controller
        assertNull(actor.getController());
        
        // But our local reference should still work
        assertNotNull(controllerRef);
        assertEquals(controller1, controllerRef);
        
        // Controller should have been unbound
        assertEquals(1, controller1.getUnbindCallCount());
    }
}