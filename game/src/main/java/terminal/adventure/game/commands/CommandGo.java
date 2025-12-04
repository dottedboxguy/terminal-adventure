
package terminal.adventure.game.commands;

import java.util.Collection;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;

/**
 * Concrete command implementation for moving between locations in the game.
 * 
 * This command allows the player to navigate through the game world by moving
 * from one location to another via available exits.
 * 
 * Usage: GO  <location_name>
 * 
 * @see Command
 * @see Exit
 * @see Location
 */
public class CommandGo extends Command {
    
    /**
     * Constructs a new GO command with the specified arguments.
     * 
     * The GO command is a terminal command, meaning successful execution
     * results in passing the player's turn.
     * 
     *
     * @param args Command arguments containing the target location name.
     *             Should contain exactly one argument specifying the destination.
     */
    public CommandGo(String[] args) {
        super(args, true);
    }

    /**
     * Executes the GO command to move the actor to a new location.
     * 
     * This method:
     *   - Validates that a destination was specified
     *   - Checks if an exit exists to the requested location from the current position
     *   - Attempts to move the actor through the exit
     *   - Returns a narrative description of the movement
     *
     * @param actor The actor attempting to move. Must not be null.
     * @return A string describing the movement and the new location.
     * @throws invalidInputException If:
     *                                   - No destination is specified (empty args)
     *                                   - The requested destination has no exit from current location
     *                                   - The exit cannot be traversed (e.g., locked, blocked)
     */
    @Override
    public String execute(Actor actor) throws invalidInputException {
        // Validate that a destination was provided
        if (args.length == 0) {
            throw new invalidInputException("Where do you wanna GO ?\n" + 
                                            this.help() + 
                                            this.getAvailableExits(actor.getCurrentLocation()));
        }
        
        // Get current location and check for exit to requested destination
        Location currentLocation = actor.getCurrentLocation();
        Exit exit = currentLocation.getExit(this.args[0]);
        
        if (exit == null) {
            throw new invalidInputException("There is no location named \"" + 
                                            String.join(" ", args) + 
                                            "\" around here.\n" + 
                                            this.getAvailableExits(actor.getCurrentLocation()));
        }
        
        // Attempt to move through the exit
        if (!actor.go(exit)) {
            throw new invalidInputException(exit.getMessage());
        }
        
        // Generate movement narrative
        Location newLocation = exit.getDestination();
        return ("You walk through " + exit.getName() + "...\n") + 
               ("You've arrived at: " + newLocation.getName() + "\n" + 
                newLocation.look());
    }
    
    /**
     * Generates a list of available exits from the specified location.
     *
     * This method queries the location for visible exits and creates a
     * user-friendly string listing all accessible destinations.
     *
     * @param location The location to check for exits. Must not be  null.
     * @return A string listing all visible exits, or a message
     *         indicating no exits are available.
     */
    private String getAvailableExits(Location location) {
        if (location.getVisibleExits().isEmpty()) {
            return "There's no exit that you can see. You're locked in.";
        }
        
        Collection<Exit> destinations = location.getVisibleExits().values();
        String res = "Available exits from here:\n";
        for (Exit exit : destinations) {
            res += (" - " + exit.getDestination().getName() + "\n");
        }
        return res;
    }
    
    /**
     * Returns help text explaining how to use the GO command.
     *
     * The help text includes the command syntax and a brief description
     * of its functionality.
     *
     * @return A string containing the command syntax and usage instructions.
     */
    @Override
    public String help() {
        return "GO <Location's name>\n" +
               "You try to go to that location if an exit allows you to do so.\n";
    }
}