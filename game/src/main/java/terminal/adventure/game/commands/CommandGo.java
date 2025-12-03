
package terminal.adventure.game.commands;

import java.util.Collection;

import terminal.adventure.game.Console;
import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.commands.CommandType;


public class CommandGo extends Command {
    
	Location target = null;
	
	public CommandGo() {
		this.type = CommandType.GO;
	}
	
	
	/*

    public void go(Actor actor, Location location) {

        if (args.length == 0) {
            System.out.println("Where do you wanna GO ?");
            System.out.println(help());
            System.out.println("\nAvailable locations from here :");
            displayAvailableExits(console.getPlayer().getActor().getCurrentLocation());
            return;
        }
        
        Location currentLocation = console.getPlayer().getActor().getCurrentLocation();
        Exit exit = currentLocation.getExit(args[0]);
        
        if (exit == null) {
            System.out.println("there is no location named \"" + String.join(" ", args) + "\" near.");
            System.out.println("\nAvailable locations from here :");
            displayAvailableExits(console.getPlayer().getActor().getCurrentLocation());
            return;
        }
        
        if (!exit.canCross()) {
            System.out.println(exit.getFailMessage());
            return;
        }
        
        Location newLocation = exit.getDestination();
        console.getPlayer().getActor().setCurrentLocation(newLocation);
        
        System.out.println("You walk through" + exit.getName() + ".");
        System.out.println("You've arrrived at : " + newLocation.getName());
        System.out.println(newLocation.getDescription());
    }
    
    private void displayAvailableExits(Location location) {
        if (location.getVisibleExits().isEmpty()) {
            System.out.println("There's no exit that you can see. You're locked in.");
        } else {
            Collection<Exit> destinations = location.getVisibleExits().values();
            for (Exit destination : destinations) {
                System.out.println(" - " + destination.getDestination().getName() + " :");
                System.out.println(destination.getDestination().getDescription());
            }
        }
        
    }
     */
    
    /**
     * Gets the Location where the actor will go
     * @return
     */
	public Location getTarget() {
    	return this.target;
    }
    
    @Override
    public String help() {
        return "GO <Location's name>\n you try to go to that location if an exit allows you to do so.\n";
    }

	@Override
	public Command copy() {
		// TODO Returns a copy of this instance.
		return this;
	}

	@Override
	public void init(Actor actor) {
		
		Collection<Exit> exits = (Collection<Exit>) actor.getCurrentLocation().getVisibleExits().values();
		
		for(Exit e : exits) {
			if (e.getDestination().getName().equals(this.args[0])){
				this.target = e.getDestination();
			}
		}
		
		if (this.target == null) {
			this.setReturnMessage("Unknown location");
		}
		
	}
}