package terminal.adventure.game;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Map;
import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.Stats;

public class LocationTest {

    @Test
    public void testLocationConstructor() {
        Location location = new Location("TestLocation", "A test location");
        assertEquals("TestLocation", location.getName());
        assertEquals("A test location", location.getDescription());
        assertTrue(location.getActors().isEmpty());
        assertTrue(location.getVisibleExits().isEmpty());
        assertTrue(location.getInteractables().isEmpty());
    }

    @Test
    public void testAddExit() {
        Location location1 = new Location("Location1", "First location");
        Location location2 = new Location("Location2", "Second location");
        Exit exit = new Exit(location2, "Door", "A wooden door") {
            @Override
            public boolean canCross() {
                return true;
            }

            @Override
            public String getMessage() {
                return "You can cross.";
            }
        };
        location1.addExit(exit);
        assertEquals(exit, location1.getExit("Location2"));
    }

    @Test
    public void testAddActor() {
        Location location = new Location("TestLocation", "A test location");
        Actor actor = new Actor("TestActor", "A test actor") {};
        location.addActor(actor);
        assertEquals(1, location.getActors().size());
        assertEquals(location, actor.getCurrentLocation());
    }

    @Test
    public void testRemoveActor() {
        Location location = new Location("TestLocation", "A test location");
        Actor actor = new Actor("TestActor", "A test actor") {};
        location.addActor(actor);
        location.removeActor(actor);
        assertTrue(location.getActors().isEmpty());
        assertNull(actor.getCurrentLocation());
    }

    @Test
    public void testAddItem() {
        Location location = new Location("TestLocation", "A test location");
        Item item = new Item("Sword", "A sharp sword", new Stats());
        location.addItem(item);
        assertEquals(1, location.getItems().size());
        assertEquals(item, location.getItems().get(0));
    }

    @Test
    public void testRemoveItem() {
        Location location = new Location("TestLocation", "A test location");
        Item item = new Item("Sword", "A sharp sword", new Stats());
        location.addItem(item);
        location.removeItem(item);
        assertTrue(location.getItems().isEmpty());
    }

    @Test
    public void testLook() {
        Location location = new Location("TestLocation", "A test location");
        String description = location.look();
        assertTrue(description.contains("A test location"));
        assertTrue(description.contains("You see :"));
    }

    @Test
    public void testGetActorByName() {
        Location location = new Location("TestLocation", "A test location");
        Actor actor = new Actor("TestActor", "A test actor") {};
        location.addActor(actor);
        List<Actor> actors = location.getActorByName("TestActor");
        assertEquals(1, actors.size());
        assertEquals(actor, actors.get(0));
    }

    @Test
    public void testGetExitsByName() {
        Location location1 = new Location("Location1", "First location");
        Location location2 = new Location("Location2", "Second location");
        Exit exit = new Exit(location2, "Door", "A wooden door") {
            @Override
            public boolean canCross() {
                return true;
            }

            @Override
            public String getMessage() {
                return "You can cross.";
            }
        };
        location1.addExit(exit);
        List<Exit> exits = location1.getExitsByName("Door");
        assertEquals(1, exits.size());
        assertEquals(exit, exits.get(0));
    }

    @Test
    public void testGetVisibleExits() {
        Location location1 = new Location("Location1", "First location");
        Location location2 = new Location("Location2", "Second location");
        Exit exit = new Exit(location2, "Door", "A wooden door") {
            @Override
            public boolean canCross() {
                return true;
            }

            @Override
            public String getMessage() {
                return "You can cross.";
            }
        };
        location1.addExit(exit);
        Map<String, Exit> visibleExits = location1.getVisibleExits();
        assertEquals(1, visibleExits.size());
        assertEquals(exit, visibleExits.get("Location2"));
    }
}
