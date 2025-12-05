package terminal.adventure.game.actors;

import java.util.List;

import junit.framework.TestCase;
import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;

// Mock Controller for testing
class MockController extends Controller {
    private Actor boundActor = null;
    private int unbindCallCount = 0;
    private int bindCallCount = 0;
    
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
    
    @Override
    public void die() {
        // Empty implementation for testing
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

public class ActorSetControllerTeststruct extends TestCase {
    
    private TestActor actor;
    private MockController controller1;
    private MockController controller2;
    private MockController controller3;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        actor = new TestActor("TestActor", "Description");
        controller1 = new MockController();
        controller2 = new MockController();
        controller3 = new MockController();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    // Test 1: No controller to New controller
    public void testSetController_FromNullToNewController() {
        // Precondition
        assertNull("Actor should not have an initial controller", actor.getController());
        
        // Execution
        actor.setController(controller1);
        
        // Verifications
        assertEquals("Actor should have the new controller", controller1, actor.getController());
        assertEquals("Controller should be bound to the actor", actor, controller1.getActor());
        assertEquals("bindActor should be called once", 1, controller1.getBindCallCount());
        assertEquals("unbindActor should not be called", 0, controller1.getUnbindCallCount());
    }
    
    // Test 2: Old controller to Same controller already bound
    public void testSetController_FromOldToSameControllerAlreadyBound() {
        // Preparation
        controller1.bindActor(actor);
        actor.setController(controller1);
        
        // Reset counters
        int initialBindCount = controller1.getBindCallCount();
        int initialUnbindCount = controller1.getUnbindCallCount();
        
        // Execution
        actor.setController(controller1); // Same instance
        
        // Verifications
        assertEquals("Actor should still have the same controller", controller1, actor.getController());
        assertEquals("Controller should still be bound to the actor", actor, controller1.getActor());
        assertEquals("bindActor should not be called again", initialBindCount, controller1.getBindCallCount());
        assertEquals("unbindActor should not be called", initialUnbindCount, controller1.getUnbindCallCount());
    }
    
    // Test 3: Old controller to New controller already bound to this actor
    public void testSetController_FromOldToNewControllerAlreadyBoundToThisActor() {
        // Preparation
        controller2.bindActor(actor);
        actor.setController(controller2);
        
        // New controller already bound to this actor
        controller1.bindActor(actor);
        
        // Execution
        actor.setController(controller1);
        
        // Verifications
        assertEquals("Actor should have the new controller", controller1, actor.getController());
        assertEquals("Controller1 should be bound to the actor", actor, controller1.getActor());
        assertEquals("Controller2 should be unbound", null, controller2.getActor());
        assertEquals("Controller2.unbindActor should be called once", 1, controller2.getUnbindCallCount());
        assertEquals("Controller1.bindActor should not be called again", 1, controller1.getBindCallCount());
    }
    
    // Test 4: Old controller to New controller bound to another actor
    public void testSetController_FromOldToNewControllerBoundToOtherActor() {
        // Preparation
        actor.setController(controller1);
        
        // Create another actor
        TestActor otherActor = new TestActor("OtherActor", "Description");
        controller2.bindActor(otherActor);
        
        // Reset counters
        int initialBindCount2 = controller2.getBindCallCount();
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have the new controller", controller2, actor.getController());
        assertEquals("Controller2 should now be bound to the actor", actor, controller2.getActor());
        assertEquals("Controller1 should be unbound", null, controller1.getActor());
        assertEquals("Controller1.unbindActor should be called once", 1, controller1.getUnbindCallCount());
        assertEquals("Controller2.bindActor should be called once more", initialBindCount2 + 1, controller2.getBindCallCount());
    }
    
    // Test 5: Old controller to New controller without actor
    public void testSetController_FromOldToNewControllerWithoutActor() {
        // Preparation
        actor.setController(controller1);
        
        // Controller2 without bound actor
        assertNull("Controller2 should not have an initial actor", controller2.getActor());
        
        // Reset counters
        int initialBindCount2 = controller2.getBindCallCount();
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have the new controller", controller2, actor.getController());
        assertEquals("Controller2 should be bound to the actor", actor, controller2.getActor());
        assertEquals("Controller1 should be unbound", null, controller1.getActor());
        assertEquals("Controller1.unbindActor should be called once", 1, controller1.getUnbindCallCount());
        assertEquals("Controller2.bindActor should be called", initialBindCount2 + 1, controller2.getBindCallCount());
    }
    
    // Additional test: Verify correct sequence
    public void testSetController_SequenceOfControllers() {
        // Step 1: No controller to controller1
        actor.setController(controller1);
        assertEquals("Step 1: controller1", controller1, actor.getController());
        assertEquals("Step 1: actor bound", actor, controller1.getActor());
        assertEquals(1, controller1.getBindCallCount());
        
        // Step 2: controller1 to controller2
        actor.setController(controller2);
        assertEquals("Step 2: controller2", controller2, actor.getController());
        assertNull("Step 2: controller1 unbound", controller1.getActor());
        assertEquals("Step 2: controller1 unbind called", 1, controller1.getUnbindCallCount());
        assertEquals("Step 2: controller2 bind called", 1, controller2.getBindCallCount());
        
        // Step 3: controller2 to controller3
        actor.setController(controller3);
        assertEquals("Step 3: controller3", controller3, actor.getController());
        assertNull("Step 3: controller2 unbound", controller2.getActor());
        assertEquals("Step 3: controller2 unbind called", 1, controller2.getUnbindCallCount());
        assertEquals("Step 3: controller3 bind called", 1, controller3.getBindCallCount());
    }
    
    // Test: Verify that clearController works with setController
    public void testSetController_AfterClearController() {
        // Preparation
        actor.setController(controller1);
        actor.clearController();
        
        assertNull("Actor should no longer have a controller", actor.getController());
        assertNull("Controller1 should be unbound", controller1.getActor());
        
        // Execution
        actor.setController(controller2);
        
        // Verifications
        assertEquals("Actor should have the new controller", controller2, actor.getController());
        assertEquals("Controller2 should be bound to the actor", actor, controller2.getActor());
        assertEquals("Controller2.bindActor should be called", 1, controller2.getBindCallCount());
    }
}