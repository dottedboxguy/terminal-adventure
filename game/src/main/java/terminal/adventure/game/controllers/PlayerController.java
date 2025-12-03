package terminal.adventure.game.controllers;
import terminal.adventure.exceptions.invalidInputException;
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
    
    // Behavior defined per type:
    // public void takeTurn(/*GameState game,*/ PlayerController playerController);

    @Override
    public int equipChooseSlot(List<String> candidatesNames) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void play() {
    	
    	boolean endTurn = false;
    	boolean failedCommand = false;
    	
    	while (!endTurn) {
    		
    		failedCommand = false;
    		Command cmd = this.console.getCommand();
    		
    		
    		
    		try {
    			
    			cmd.execute(this.getActor());    			
    		
    		} catch (invalidInputException e) {
    		
    			console.print(e.getMessage());
    			failedCommand = true;
    		}
    		
    		
    		
    		endTurn = ( !failedCommand && cmd.isTerminal() );
    		
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

