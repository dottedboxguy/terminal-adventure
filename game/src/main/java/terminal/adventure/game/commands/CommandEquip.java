package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;

public class CommandEquip extends Command{

	public CommandEquip(String[] args) {
		super(args, true);
	}

	@Override
	public String execute(Actor actor) throws invalidInputException {

		if (args.length == 0) {
			return this.help();
		}
		
		List<Item> tempRes;

		List<Storage> sources = actor.getAllStorages();
		sources.add(actor.getCurrentLocation());
		
		for ( Storage s : sources ) {
			tempRes = s.searchItems(args[0]);
			if (!tempRes.isEmpty()) {
				
				if (actor.equipFrom(tempRes.get(0), actor.getController(), s)) {
					return "You equipped "+tempRes.get(0).getName()+"\n";					
				}else{
					return "You cannot equip "+tempRes.get(0).getName()+"\n";
				}
				
			}
		}
		return "You don't find anything with that name.\n";
	}

	@Override
	public String help() {
		return "What do you want to EQUIP ?\n usage : EQUIP <item name>\n";
	}

}
