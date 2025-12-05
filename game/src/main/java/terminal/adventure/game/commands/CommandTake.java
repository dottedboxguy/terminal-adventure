package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;

/**
 * Concrete command implementation for picking up items from the current location.
 * 
 * The TAKE command allows the player to collect items from their current location
 * and add them to their inventory. Multiple items can be taken at once if they
 * match the specified item name(s). This is a non-terminal command that does not
 * consume a turn.
 * 
 * @see Command
 * @see Item
 * @see Actor
 */
public class CommandTake extends Command {
    
    /**
     * Constructs a new TAKE command.
     * 
     * The TAKE command is non-terminal, meaning it can be executed without
     * ending the player's turn.
     *
     * @param args Command arguments containing the name(s) of item(s) to pick up.
     *             Multiple items can be specified as separate arguments.
     */
    public CommandTake(String[] args) {
        super(args, false);
    }

    /**
     * Executes the TAKE command to pick up items from the current location.
     * 
     * This method:
     *   - Iterates through all specified item names in the arguments
     *   - Searches the current location for items matching each name
     *   - Attempts to transfer each found item to the actor's inventory
     *   - Returns a detailed result message indicating success/failure for each item
     *
     * @param actor The actor attempting to pick up items. Must not be null.
     * @return A string containing the result of each attempted item pickup,
     *         indicating whether items were found and added to inventory.
     */
    @Override
    public String execute(Actor actor){
        String result = "";
        
        // Process each item name specified in the arguments
        for (String arg : args) {
            result += arg + ": ";
            
            // Search for items in current location matching the name
            List<Item> itemsToAdd = actor.getCurrentLocation().searchItems(arg);
            
            if (itemsToAdd == null || itemsToAdd.isEmpty()) {
                result += "not found\n";
            }
            else{
                // take each found item
                for (Item item : itemsToAdd) {
                    if (!actor.takeItem(item, actor.getCurrentLocation())) {
                    	return "You have nowhere to store this!";
                    };
                }
                result += "added\n";
            }
        }
        
        return result;
    }

    /**
     * Returns help text explaining how to use the TAKE command.
     *
     * @return A string describing the TAKE command's syntax and functionality.
     */
    @Override
    public String help() {
        return "TAKE <item> -> put the item from the ground and add it into your inventory.";
    }
}