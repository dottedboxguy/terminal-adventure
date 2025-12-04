
package terminal.adventure.game.commands;

import java.util.Collection;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;

public class CommandGo extends Command {
    
	public CommandGo(String[] args){
		super(args, true);
	}

    @Override
    public String execute(Actor actor) throws invalidInputException {

        if (args.length == 0) {
            throw new invalidInputException("Where do you wanna GO ?\n" + this.help() + this.getAvailableExits(actor.getCurrentLocation()));

        }
        
        Location currentLocation = actor.getCurrentLocation();
        Exit exit = currentLocation.getExit(this.args[0]);
        
        if (exit == null) {
            throw new invalidInputException("there is no location named \"" + String.join(" ", args) + "\" near." + this.getAvailableExits(actor.getCurrentLocation()));
        }
        
        if (!actor.go(exit)) {
            throw new invalidInputException(exit.getMessage());
        }
        
        Location newLocation = exit.getDestination();
        return ("You walk through" + exit.getName() + "...\n") + ("You've arrrived at : " + newLocation.getName() +"\n" + newLocation.look());
    }
    
    private String getAvailableExits(Location location) {
        if (location.getVisibleExits().isEmpty()) {
            return "There's no exit that you can see. You're locked in.";
        }
        Collection<Exit> destinations = location.getVisibleExits().values();
        String res = "Available exits from here :\n";
        for (Exit exit : destinations) {
            res += (" - " + exit.getDestination().getName() + " :\n");
            res += (exit.getDestination().look() + "\n");
        }
        return res;
        
    }
    
    @Override
    public String help() {
        return "GO <Location's name>\n you try to go to that location if an exit allows you to do so.\n";
    }

}