package terminal.adventure.game.controllers;
import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Console;
import terminal.adventure.game.commands.Command;

public class PlayerController extends Controller{

	private Console console;
	
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
    	boolean failedCommand;
    	
    	while (!endTurn) {
    		
    		failedCommand = false;
    		Command cmd = this.console.getAction();
    		
    		
    		
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

    public Console getConsole(){
        return this.console;
    }
    
}

