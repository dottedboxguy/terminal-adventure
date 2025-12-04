package terminal.adventure.game.commands;

import terminal.adventure.game.Console;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.controllers.PlayerController;

/**
 * Concrete command implementation for terminating the game session.
 * <p>
 * The QUIT command provides a controlled way to exit the game. When executed,
 * it displays a farewell message and terminates the application via System.exit().
 * This is a terminal command that ends the game session completely.
 * </p>
 * 
 * @see Command
 * @see Console
 * @see PlayerController
 */
public class CommandQuit extends Command {

    /**
     * Constructs a new QUIT command.
     * 
     * The QUIT command is a terminal command, as indicated by the true
     * value passed to the parent constructor.
     * 
     * @param args Command arguments (unused for QUIT command)
     */
    public CommandQuit(String[] args) {
        super(args, true); 
    }

    /**
     * Executes the QUIT command to terminate the game.
     * 
     * This method:
     *   - Retrieves the console from the player's controller
     *   - Displays a farewell message indicating the game is ending
     *   - Calls System.exit(0) to terminate the JVM process
     * 
     * Note: The return value is never actually used since the application
     * terminates before it can be processed, but it's required by the interface.
     *
     * @param actor The actor attempting to quit the game. Must not be null
     *              and must have a PlayerController assigned.
     * @return An empty string (never actually used due to program termination)
     */
    @Override
    public String execute(Actor actor){
        // Retrieve console to display exit message
        Console console = ((PlayerController) actor.getController()).getConsole();
        console.print("Leaving the game...");
        
        // Terminate the application
        System.exit(0);
        
        // This return statement is never reached but required by the interface
        return ""; 
    }

    /**
     * Returns help text explaining how to use the QUIT command.
     * This method provides a concise description of the QUIT command's purpose.
     *
     * @return A string describing the QUIT command's functionality.
     */
    @Override
    public String help() {
        return "QUIT -> quits the game.";
    }
}