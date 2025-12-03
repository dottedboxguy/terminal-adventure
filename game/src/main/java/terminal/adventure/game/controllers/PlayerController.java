package terminal.adventure.game.controllers;
import terminal.adventure.game.Console;
import java.util.List;

import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.commands.Command;
import terminal.adventure.game.commands.CommandGo;
import terminal.adventure.game.commands.CommandType;
import terminal.adventure.game.exits.Exit;

import java.util.concurrent.TimeUnit;

public class PlayerController extends Controller{

	Console console;
	
    public PlayerController(Faction faction, Console console) {
    	super(faction);
    	this.console = console;
    }

    // Basic universal actions:
    public void attack(Actor actor) {
    	//
    }

    // Behavior defined per type:
    // public void takeTurn(/*GameState game,*/ PlayerController playerController);

    @Override
    public int equipChooseSlot(List<String> candidatesNames) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void play() {
    	
    	System.out.println("DEBUG PlayerController play\n");
    	
    	for ( Exit e : this.getActor().getCurrentLocation().getVisibleExits().values() ) {
    		System.out.println(e.getDestination().getName());
    	}
    	
    	
    	
    	Command cmd = this.console.getAction();
    	
    	//TODO
    	switch (cmd.getType()) {
    	case GO :
    		cmd.init(this.getActor());
    		Location target = ((CommandGo) cmd).getTarget();
    		
    		System.out.println("DEBUG PlayerController play\n target :"+target);
    		
    		if(target != null) {
    			
    			console.print(
    					this.actor.go(target)
    					);
    		
    		} else {
    			console.print(cmd.getReturnMessage());    			
    		}
    		
    		
    		break;
    	case LOOK :
    		break;
    	case USE :
    		break;
    	case TAKE :
    		break;
    	}
    	
    	
    	
    	System.out.println("DEBUG : PlayerController got command :"+cmd);
    	
    	try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }

    // ---------------------------------------
    // ##  Methods IN GAME chat...
    // ---------------------------------------

    public void movePlayer(Actor actor, Location newLocation){
        String message = actor.Move(newLocation);
        //this.console.print(message);
    }
}
