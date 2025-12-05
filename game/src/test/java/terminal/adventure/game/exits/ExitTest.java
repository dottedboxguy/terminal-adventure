package terminal.adventure.game.exits;

import org.junit.Test;
import static org.junit.Assert.*;
import terminal.adventure.game.Location;
import terminal.adventure.game.exits.Exit;

public class ExitTest {

    private class TestExit extends Exit {
        public TestExit(Location destination, String name, String description) {
            super(destination, name, description);
        }

        @Override
        public boolean canCross() {
            return true;
        }

        @Override
        public String getMessage() {
            return "You can cross.";
        }
    }

    @Test
    public void testExitConstructor() {
        Location destination = new Location("TestLocation", "A test location");
        Exit exit = new TestExit(destination, "Door", "A wooden door");
        assertEquals("Door", exit.getName());
        assertEquals("A wooden door", exit.look());
        assertEquals(destination, exit.getDestination());
    }

    @Test
    public void testCanCross() {
        Location destination = new Location("TestLocation", "A test location");
        Exit exit = new TestExit(destination, "Door", "A wooden door");
        assertTrue(exit.canCross());
    }

    @Test
    public void testGetMessage() {
        Location destination = new Location("TestLocation", "A test location");
        Exit exit = new TestExit(destination, "Door", "A wooden door");
        assertEquals("You can cross.", exit.getMessage());
    }
}
