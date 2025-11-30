package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.game.Console;
import terminal.adventure.game.interactables.Interactable;

public class CommandUse implements Command {

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

    @Override
    public String help() {
        return "\n Usage : \n USE \t <arg> \n\t <toUse> <usedOn>";
    }
}