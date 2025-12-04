package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Console;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.interactables.Interactable;

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
		// TODO
		return null;
	}
}