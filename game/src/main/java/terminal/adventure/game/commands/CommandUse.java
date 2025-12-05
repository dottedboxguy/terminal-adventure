package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.interactables.Interactable;
import terminal.adventure.game.inventory.items.Item;

public class CommandUse extends Command {

	/*
    @Override
    public void execute(String[] args, Console console) {

        if (args.length == 1) {

            List<Interactable> interactables = console.getPlayer().getActor().getCurrentLocation().getInteractables();
            for (Interactable interactable : interactables) {

                if (interactable.getName().equals(args[0])) {
                    interactable.action();
                }
            }
        }
        
    }
	*/
	
	
    public CommandUse(String[] args) {
		super(args, true);
	}

	@Override
    public String help() {
        return "\n Usage : \n USE \t <arg> \n\t <toUse> <usedOn>";
    }

	@Override
	public String execute(Actor actor) throws invalidInputException {
        List<Interactable> interactables = actor.getCurrentLocation().getInteractables();
        if (this.args.length == 1) {
            for (Interactable i : interactables){
                if (i.getName() == this.args[0])
                    return i.action(actor);
            }
            return "hmm... you couldn't find any "+args[0]+" around";
	    }
        if (this.args.length == 2){
            for (Interactable i : interactables){
                if (i.getName() == this.args[1]){
                    for (Item item : actor.getFirstStorage().getItems()){
                        if (item.getName() == this.args[0]){
                            return i.actionWithItem(actor, item);
                        }
                    }
                    return "hmm... you can't find any "+this.args[0]+" in your inventory";
                }
            }
            for (Exit exit : actor.getCurrentLocation().getVisibleExits().values()) {
                if (exit.getName() == this.args[1]){
                    if (exit instanceof Interactable){
                        for (Item item : actor.getFirstStorage().getItems()){
                            if (item.getName() == this.args[0]){
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