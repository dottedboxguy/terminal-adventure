package terminal.adventure.game.commands;

import java.util.function.Function;

import terminal.adventure.game.Console;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.controllers.PlayerController;

/**
 * Concrete command implementation for displaying help information about available commands.
 * 
 * The HELP command provides the player with a list of all registered commands
 * along with their help descriptions. It retrieves command information from
 * the game console's command registry.
 * 
 * 
 * @see Command
 * @see Console
 * @see PlayerController
 */
public class CommandHelp extends Command {

    /**
     * Constructs a new HELP command.
     * 
     * The HELP command is non-terminal, meaning it can be executed without
     * ending the player's turn
     *
     * @param args Command arguments (not used for HELP command)
     */
    public CommandHelp(String[] args) {
        super(args, false);
    }

    /**
     * Executes the HELP command to display all available commands.
     * 
     * This method:
     *   - Retrieves the player's controller to access the game console
     *   - Gets the command registry from the console
     *   - Iterates through all registered commands
     *   - Instantiates each command to retrieve its help text
     *   - Formats the list of commands and their descriptions
     *
     * @param actor The actor requesting help. Must not be null and must
     *              have a PlayerController assigned.
     * @return A formatted string listing all available commands with their
     *         descriptions, or an empty list if no commands are registered.
     */
    @Override
    public String execute(Actor actor) {
        String res = "Available commands:\n";
        
        // Get the console from the player's controller
        Console console = ((PlayerController) actor.getController()).getConsole();
        
        // Iterate through all registered commands
        for (String name : console.getCommands().keySet()) {
            Function<String[], Command> commandFactory = console.getCommands().get(name);
            Command cmd = commandFactory.apply(null); // Create command instance without args
            
            String description = "";
            if (cmd != null) {
                description = cmd.help(); // Get command-specific help
            }
            res += (" - " + name + " : " + description + "\n");
        }
        return res;
    }

    /**
     * Returns help text explaining how to use the HELP command.
     * This method provides a brief description of what the HELP command does.
     * 
     * @return A string describing the HELP command's functionality.
     */
    @Override
    public String help() {
        return "HELP -> print all the commands you can use.";
    }

}