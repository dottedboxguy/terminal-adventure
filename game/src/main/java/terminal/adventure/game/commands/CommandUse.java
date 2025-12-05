package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.interactables.Interactable;
import terminal.adventure.game.inventory.items.Item;

public class CommandUse extends Command {
	
    /**
     * Constructs a new USE command.
     * 
     * The USE command is terminal, meaning execution will end the player's turn
     *
     * @param args either :
     *                 [name of an interactable]
     *                 [name of an item] [name of an interactable]
     *                 [name of an item] [name of an interactable exit]
     */
    public CommandUse(String[] args) {
		super(args, true);
	}

    /**
     * Returns help text explaining how to use the TAKE command.
     *
     * @return A string describing the TAKE command's syntax and functionality.
     */
	@Override
    public String help() {
        return "\n Usage : \n USE \t <arg> \n\t <toUse> <usedOn>";
    }

    /**
     * Executes the USE command to interact with various game elements
     * 
     * This method:
     *   - Iterates through all interactables and exits in the room of the player
     *     to find one of matching name
     *   - Iterates through all the items of the player's inventory to find one of
     *     matching name
     *   - Returns a detailed result message indicating what happened after trying
     *     to execute the command
     *
     * @param actor The actor attempting use whatever he wants to use. Must not be null.
     * @return A string containing the result of the action
     */
	@Override
	public String execute(Actor actor) throws invalidInputException {
        List<Interactable> interactables = actor.getCurrentLocation().getInteractables();
        if (this.args.length == 1) {
            for (Interactable i : interactables){
                if (i.getName().equals(this.args[0]))
                    return i.action(actor);
            }
            return "hmm... you couldn't find any "+args[0]+" around";
	    }
        if (this.args.length == 2){
            for (Interactable i : interactables){
                if (i.getName().equals(this.args[1])){
                    for (Item item : actor.getFirstStorage().getItems()){
                        if (item.getName() == this.args[0]){
                            return i.actionWithItem(actor, item);
                        }
                    }
                    return "hmm... you can't find any "+this.args[0]+" in your inventory";
                }
            }
            for (Exit exit : actor.getCurrentLocation().getVisibleExits().values()) {
                if (exit.getName().equals(this.args[1])){
                    if (exit instanceof Interactable){
                        for (Item item : actor.getFirstStorage().getItems()){
                            if (item.getName().equals(this.args[0])){
                                return ((Interactable)exit).actionWithItem(actor, item);
                            }
                        }
                        return "hmm... you can't find any "+this.args[0]+" in your inventory";
                    }
                    return "you can't seem to be able to do anything with this exit";
                }
            }
            return "hmm... you couldn't find any "+this.args[1]+" around";
        }
        return "You don't really know why you would do that";
    }
}