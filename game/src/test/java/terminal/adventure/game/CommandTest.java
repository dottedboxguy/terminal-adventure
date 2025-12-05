package terminal.adventure.game;
import org.junit.Test;
import static org.junit.Assert.*;
import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.commands.Command;

public class CommandTest {

    private class TestCommand extends Command {
        public TestCommand(String[] args, boolean isTerminal) {
            super(args, isTerminal);
        }

        @Override
        public String execute(Actor actor) throws invalidInputException {
            return "Command executed";
        }

        @Override
        public String help() {
            return "Test command help";
        }
    }

    @Test
    public void testIsTerminal() {
        Command terminalCommand = new TestCommand(new String[0], true);
        Command nonTerminalCommand = new TestCommand(new String[0], false);
        assertTrue(terminalCommand.isTerminal());
        assertFalse(nonTerminalCommand.isTerminal());
    }

    @Test
    public void testHelp() {
        Command command = new TestCommand(new String[0], false);
        assertEquals("Test command help", command.help());
    }

    @Test
    public void testExecute() throws invalidInputException {
        Command command = new TestCommand(new String[0], false);
        Actor actor = new Actor("TestActor", "A test actor") {};
        assertEquals("Command executed", command.execute(actor));
    }

    @Test(expected = invalidInputException.class)
    public void testExecuteWithException() throws invalidInputException {
        Command failingCommand = new Command(new String[0], false) {
            @Override
            public String execute(Actor actor) throws invalidInputException {
                throw new invalidInputException("Invalid input");
            }

            @Override
            public String help() {
                return "Failing command help";
            }
        };
        Actor actor = new Actor("TestActor", "A test actor") {};
        failingCommand.execute(actor);
    }
}
