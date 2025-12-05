package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;

/**
 * Concrete command implementation for examining the environment.
 * 
 * The LOOK command allows the player to examine their surroundings or specific
 * elements within their current location. When used without arguments, it
 * provides a general description of the current location. When used with
 * arguments, it provides detailed information about specific actors or exits.
 * This is a non-terminal command that does not consume a turn.
 * 
 * @see Command
 * @see Actor
 * @see Exit
 */
public class CommandLook extends Command {

    /**
     * Constructs a new LOOK command.
     * 
     * The LOOK command is non-terminal, meaning it can be executed without
     * ending the player's turn.
     * 
     *
     * @param args Command arguments specifying what to examine.
     *             If empty, examines the current location.
     *             If provided, examines specific actors or exits by name.
     */
    public CommandLook(String[] args) {
        super(args, false);
    }

    /**
     * Executes the LOOK command to examine the environment.
     * 
     * This method:
     * 
     *   - If no arguments are provided: returns a general description of the current location
     *   - If arguments are provided: examines each argument sequentially
     *   - For each argument, searches for matching actors and exits in the current location
     *   - Returns descriptive information about found actors and exits
     *   - Provides feedback when nothing matches the given argument
     *
     * @param actor The actor performing the examination. Must not be null.
     * @return A string containing descriptive information about:
     *           - The current location (if no arguments)
     *           - Matching actors and exits (if arguments provided)
     *           - A message when nothing matches the search criteria
     */
    @Override
    public String execute(Actor actor) {
        // No arguments: examine current location
        if (args.length == 0) {
            return actor.getCurrentLocation().look();
        }
        
        String result = "";
        
        // Process each argument: examine specific elements
        for (String arg : args) {
            boolean foundSomething = false;
            
            // Examine actors matching the argument
            List<Actor> actors = actor.getCurrentLocation().getActorByName(arg);
            for (Actor a : actors) {
                result += a.look() + "\n";
                foundSomething = true;
            }
            
            // Examine exits matching the argument
			List<Exit> exits = actor.getCurrentLocation().getExitsByName(arg);
			
            for (Exit exit : exits) {
                result += exit.look() + "\n";
                foundSomething = true;
            }
            
            // Provide feedback if nothing was found for this argument
            if (!foundSomething) {
                result += "There is nothing to show for '" + arg +"' in here.\n";
            }
        }
        
        return result;
    }

    /**
     * Returns help text explaining how to use the LOOK command.
     *
     * @return A string describing the LOOK command's syntax and functionality.
     */
    @Override
    public String help() {
        return "LOOK -> show informations about your current Location\n" +
               "LOOK <argument> ... -> show informations about the arguments you gave";
    }
}