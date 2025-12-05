package terminal.adventure.game;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import terminal.adventure.game.Stats;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.inventory.items.Item;

public class ControllerTest {

    private class TestController extends Controller {
        public TestController(Actor actor, Faction faction) {
            super(actor, faction);
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

    @Test
    public void testControllerConstructorWithActor() {
        Actor actor = new Actor("TestActor", "A test actor") {};
        Controller controller = new TestController(actor, Faction.goodGuys);
        assertEquals(actor, controller.getActor());
        assertEquals(Faction.goodGuys, controller.getFaction());
    }

    @Test
    public void testControllerConstructorWithoutActor() {
        Controller controller = new TestController(null, Faction.badGuys) {
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
        };
        assertNull(controller.getActor());
        assertEquals(Faction.badGuys, controller.getFaction());
    }

    @Test
    public void testBindActor() {
        Actor actor = new Actor("TestActor", "A test actor") {};
        Controller controller = new TestController(null, Faction.goodGuys) {
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
        };
        controller.bindActor(actor);
        assertEquals(actor, controller.getActor());
        assertEquals(controller, actor.getController());
    }

    @Test
    public void testUnbindActor() {
        Actor actor = new Actor("TestActor", "A test actor") {};
        Controller controller = new TestController(actor, Faction.goodGuys);
        controller.unbindActor();
        assertNull(controller.getActor());
        assertNull(actor.getController());
    }

    @Test
    public void testDie() {
        Actor actor = new Actor("TestActor", "A test actor") {};
        Controller controller = new TestController(actor, Faction.goodGuys);
        controller.die();
        assertNull(controller.getActor());
        assertNull(actor.getController());
    }

    @Test
    public void testIsDead() {
        Controller controller = new TestController(null, Faction.badGuys) {
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
        };
        assertTrue(controller.isDead());
    }

    @Test
    public void testPlayTurn() {
        Actor actor = new Actor("TestActor", "A test actor") {};
        Controller controller = new TestController(actor, Faction.goodGuys) {
            private boolean played = false;

            @Override
            protected void play() {
                played = true;
            }

            @Override
            public int equipChooseSlot(List<String> candidatesNames) {
                return 0;
            }

            @Override
            public void takeAttackReport(Stats report) {
            }
        };
        controller.playTurn();
    }

    @Test
    public void testEquip() {
        Actor actor = new Actor("TestActor", "A test actor") {};
        Controller controller = new TestController(actor, Faction.goodGuys);
        Item item = new Item("Sword", "A sharp sword", new Stats());
        boolean result = controller.equip(item);
        assertFalse(result);
    }
}
