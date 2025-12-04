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
    
    @Override
    /**
     * See {@link Controller}
     */
    public int equipChooseSlot(List<String> candidatesNames) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * See {@link Controller}
     */
    protected void play() {
    	
    	boolean endTurn = false;
    	boolean failedCommand;
    	
    	while (!endTurn) {
    		
    		failedCommand = false;
    		Command cmd = this.console.getAction();
    		String message = "\n";
    		
    		
    		try {
    			
    		    message = cmd.execute(this.getActor());

    		
    		} catch (invalidInputException e) {
    		
    			console.print(e.getMessage());
    			failedCommand = true;
    		}
    		console.print(message);
    		
    		endTurn = ( !failedCommand && cmd.isTerminal() );
    		
    	}
    	
    }

    // ---------------------------------------
    // ##  Methods IN GAME chat...
    // ---------------------------------------

    /**
     * @return the console that can be asked for commands.
     */
    public Console getConsole(){
        return this.console;
    }
    
}

