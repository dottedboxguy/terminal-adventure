package terminal.adventure.game.actors;

import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
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

public class ActorSetControllerTestfonct {
    
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
    
    // ==== FUNCTIONAL TESTS FOR setController ====
    
    @Test
    public void testSetController_ActorWithoutController_GetsNewController() {
        // Precondition: actor has no controller
        assertNull("Actor should not have an initial controller", actor.getController());
        
        // Execution
        actor.setController(controller1);
        
        // Verifications
        assertEquals("Actor should have the new controller", controller1, actor.getController());
        assertEquals("Controller1 should be bound to the actor", actor, controller1.getActor());
        assertEquals("bindActor should be called once", 1, controller1.getBindCallCount());
        assertEquals("unbindActor should not be called", 0, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testSetController_ActorWithController_ChangesToNewController() {
        // Preparation
        actor.setController(controller1);
        
        // Execution: change controller
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have the new controller", controller2, actor.getController());
        assertNull("Controller1 should be unbound", controller1.getActor());
        assertEquals("Controller1.unbindActor should be called once", 1, controller1.getUnbindCallCount());
        assertEquals("Controller2 should be bound to the actor", actor, controller2.getActor());
        assertEquals("Controller2.bindActor should be called once", 1, controller2.getBindCallCount());
    }
    
    @Test
    public void testSetController_SameControllerAlreadyAssigned_NoAction() {
        // Preparation
        controller1.bindActor(actor);
        actor.setController(controller1);
        
        int bindCountBefore = controller1.getBindCallCount();
        int unbindCountBefore = controller1.getUnbindCallCount();
        
        // Execution: reassign same controller
        actor.setController(controller1);
        
        // Verifications: no action should be performed
        assertEquals("Actor should still have the same controller", controller1, actor.getController());
        assertEquals("Controller should still be bound to the actor", actor, controller1.getActor());
        assertEquals("bindActor should not be called again", bindCountBefore, controller1.getBindCallCount());
        assertEquals("unbindActor should not be called", unbindCountBefore, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testSetController_NewControllerAlreadyBoundToThisActor_JustReferenceChange() {
        // Preparation
        actor.setController(controller1);
        
        // Controller2 is already bound to this actor (unlikely but possible scenario)
        controller2.bindActor(actor);
        
        int unbindCount1Before = controller1.getUnbindCallCount();
        int bindCount2Before = controller2.getBindCallCount();
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have controller2", controller2, actor.getController());
        assertEquals("Controller2 should still be bound", actor, controller2.getActor());
        assertNull("Controller1 should be unbound", controller1.getActor());
        assertEquals("Controller1.unbindActor called once", unbindCount1Before + 1, controller1.getUnbindCallCount());
        assertEquals("Controller2.bindActor not recalled (already bound)", bindCount2Before, controller2.getBindCallCount());
    }
    
    @Test
    public void testSetController_NewControllerBoundToOtherActor_RebindNeeded() {
        // Preparation
        actor.setController(controller1);
        
        TestActor otherActor = new TestActor("OtherActor", "Description");
        controller2.bindActor(otherActor);
        
        int bindCount2Before = controller2.getBindCallCount();
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have controller2", controller2, actor.getController());
        assertEquals("Controller2 should now be bound to this actor", actor, controller2.getActor());
        assertNull("Controller1 should be unbound", controller1.getActor());
        assertNull("OtherActor should be unbound from controller2", otherActor.getController());
        assertEquals("Controller2.bindActor called once more", bindCount2Before + 1, controller2.getBindCallCount());
    }
    
    @Test
    public void testSetController_NewControllerWithoutActor_BindNeeded() {
        // Preparation
        actor.setController(controller1);
        
        // Controller2 without bound actor
        assertNull("Controller2 should not have an initial actor", controller2.getActor());
        
        int bindCount2Before = controller2.getBindCallCount();
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have controller2", controller2, actor.getController());
        assertEquals("Controller2 should be bound to the actor", actor, controller2.getActor());
        assertNull("Controller1 should be unbound", controller1.getActor());
        assertEquals("Controller2.bindActor called", bindCount2Before + 1, controller2.getBindCallCount());
    }
    
    @Test
    public void testSetController_ComplexControllerSequence() {
        // Step 1: No controller → controller1
        actor.setController(controller1);
        assertEquals("Step 1: controller1", controller1, actor.getController());
        assertEquals("Step 1: actor bound", actor, controller1.getActor());
        assertEquals(1, controller1.getBindCallCount());
        assertEquals(0, controller1.getUnbindCallCount());
        
        // Step 2: controller1 → controller2
        actor.setController(controller2);
        assertEquals("Step 2: controller2", controller2, actor.getController());
        assertNull("Step 2: controller1 unbound", controller1.getActor());
        assertEquals("Step 2: controller1 unbind called", 1, controller1.getUnbindCallCount());
        assertEquals("Step 2: controller2 bind called", 1, controller2.getBindCallCount());
        
        // Step 3: controller2 → controller3
        actor.setController(controller3);
        assertEquals("Step 3: controller3", controller3, actor.getController());
        assertNull("Step 3: controller2 unbound", controller2.getActor());
        assertEquals("Step 3: controller2 unbind called", 1, controller2.getUnbindCallCount());
        assertEquals("Step 3: controller3 bind called", 1, controller3.getBindCallCount());
        
        // Step 4: controller3 → controller1 (back to first)
        actor.setController(controller1);
        assertEquals("Step 4: back to controller1", controller1, actor.getController());
        assertNull("Step 4: controller3 unbound", controller3.getActor());
        assertEquals("Step 4: controller1 bind recalled", 2, controller1.getBindCallCount());
    }
    
    @Test
    public void testSetController_AfterClearController() {
        // Preparation
        actor.setController(controller1);
        actor.clearController();
        
        assertNull("Actor should no longer have a controller", actor.getController());
        assertNull("Controller1 should be unbound", controller1.getActor());
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have controller2", controller2, actor.getController());
        assertEquals("Controller2 should be bound to the actor", actor, controller2.getActor());
        assertEquals("Controller2.bindActor called once", 1, controller2.getBindCallCount());
    }
    
    @Test
    public void testSetController_WithNull_ShouldDisconnect() {
        // Preparation
        actor.setController(controller1);
        
        // Execution: setController(null)
        actor.setController(null);
        
        // Verifications
        assertNull("Actor should no longer have a controller", actor.getController());
        assertNull("Controller1 should be unbound", controller1.getActor());
        assertEquals("Controller1.unbindActor called once", 1, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testSetController_NullThenNewController() {
        // Preparation
        actor.setController(controller1);
        actor.setController(null);
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have controller2", controller2, actor.getController());
        assertEquals("Controller2 should be bound to the actor", actor, controller2.getActor());
        assertEquals("Controller2.bindActor called once", 1, controller2.getBindCallCount());
    }
    
    @Test
    public void testSetController_OldControllerProperlyUnbound() {
        // Preparation with 2 actors sharing controller1
        TestActor actor2 = new TestActor("Actor2", "Description");
        actor.setController(controller1);
        actor2.setController(controller1);
        
        // Execution: actor changes controller
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have controller2", controller2, actor.getController());
        assertEquals("Controller2 should be bound to actor", actor, controller2.getActor());
        assertEquals("Actor2 should still have controller1", controller1, actor2.getController());
    }
    
    @Test
    public void testSetController_MultipleCallsToSameController() {
        // Execution: call setController 5 times with same controller
        for (int i = 0; i < 5; i++) {
            actor.setController(controller1);
        }
        
        // Verifications: only 1 bind and 0 unbind
        assertEquals("Actor should have controller1", controller1, actor.getController());
        assertEquals("Controller1 should be bound to the actor", actor, controller1.getActor());
        assertEquals("bindActor called exactly once", 1, controller1.getBindCallCount());
        assertEquals("unbindActor not called", 0, controller1.getUnbindCallCount());
    }
    
    @Test
    public void testSetController_StateIntegrityAfterMultipleChanges() {
        // Series of changes
        actor.setController(controller1);
        actor.setController(controller2);
        actor.setController(controller1); // Back to first
        actor.setController(controller3);
        actor.setController(controller2); // Back to second
        
        // Final verifications
        assertEquals("Final controller should be controller2", controller2, actor.getController());
        assertEquals("Controller2 should be bound", actor, controller2.getActor());
        
        // Verify other controllers are unbound
        assertNull("Controller1 should be unbound", controller1.getActor());
        assertNull("Controller3 should be unbound", controller3.getActor());
        
        // Verify calls
        assertEquals("Controller1: 2 binds, 2 unbinds", 2, controller1.getBindCallCount());
        assertEquals("Controller1: 2 unbinds", 2, controller1.getUnbindCallCount());
        assertEquals("Controller2: 2 binds, 1 unbind", 2, controller2.getBindCallCount());
        assertEquals("Controller2: 1 unbind", 1, controller2.getUnbindCallCount());
        assertEquals("Controller3: 1 bind, 1 unbind", 1, controller3.getBindCallCount());
        assertEquals("Controller3: 1 unbind", 1, controller3.getUnbindCallCount());
    }
    
    @Test
    public void testSetController_WhenActorIsDead() {
        // Simulate dead actor
        actor.getBaseStats().setCurrentHealth(0);
        assertTrue("Actor should be considered dead", actor.isDead());
        
        // Try to change controller
        actor.setController(controller1);
        
        // Verify it still works
        assertEquals("Dead actor can still change controller", controller1, actor.getController());
    }
    
    @Test
    public void testSetController_InteractionWithClearController() {
        // Preparation
        actor.setController(controller1);
        assertEquals(1, controller1.getBindCallCount());
        
        // clearController
        actor.clearController();
        assertEquals(1, controller1.getUnbindCallCount());
        assertNull(actor.getController());
        assertNull(controller1.getActor());
        
        // setController with same controller
        actor.setController(controller1);
        assertEquals("bindActor recalled after clear", 2, controller1.getBindCallCount());
        assertEquals(controller1, actor.getController());
        assertEquals(actor, controller1.getActor());
    }
    
    @Test
    public void testSetController_ControllerWithNullGetActor() {
        // Create a controller that returns null for getActor()
        MockController nullController = new MockController() {
            @Override
            public Actor getActor() {
                return null;
            }
        };
        
        actor.setController(nullController);
        
        assertEquals("Actor should have nullController", nullController, actor.getController());
        assertNull("nullController.getActor() should return null", nullController.getActor());
    }
    
    @Test
    public void testSetController_ControllerConsistency() {
        // Perform various operations
        actor.setController(controller1);
        actor.setController(controller2);
        actor.clearController();
        actor.setController(controller3);
        actor.setController(controller1);
        actor.setController(null);
        actor.setController(controller2);
        
        // Final state should be consistent
        assertEquals("Final controller should be controller2", controller2, actor.getController());
        assertEquals("controller2 should be bound to actor", actor, controller2.getActor());
        
        // Previous controllers should be unbound
        assertNull("controller1 should be unbound", controller1.getActor());
        assertNull("controller3 should be unbound", controller3.getActor());
    }
}