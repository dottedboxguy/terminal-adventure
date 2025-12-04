package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;


public class CommandTake extends Command {
    
    public CommandTake(String[] args) {
		super(args, false);
	}

	@Override
	public String execute(Actor actor) throws invalidInputException {
		for (String arg : args){
            List<Item> itemsToAdd = actor.getCurrentLocation().searchItems(arg);
            for (Item item : itemsToAdd) {
                actor.takeItem(item);
            }
        }
		return null;
	}
    
    @Override
    public String help() {
        return "\nTAKE <item> -> put the item into your inventory.";
    }

}