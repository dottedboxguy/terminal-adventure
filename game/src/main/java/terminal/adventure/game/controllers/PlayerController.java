package terminal.adventure.game.controllers;
import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Console;
import terminal.adventure.game.commands.Command;

public class PlayerController extends Controller{

	private final Console CONSOLE;
	
    public PlayerController(Faction faction, Console console) {
    	super(faction);
    	this.CONSOLE = console;
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
    		Command cmd = this.CONSOLE.getAction();
    		String message = "\n";
    		
    		if (cmd != null) {
    			
	    		try {
	    			
	    		    message = cmd.execute(this.getActor());
	
	    		
	    		} catch (invalidInputException e) {
	    		
	    			CONSOLE.print(e.getMessage());
	    			failedCommand = true;
	    		}
	    		CONSOLE.print(message);
	    		
	    		endTurn = ( !failedCommand && cmd.isTerminal() );
    		}
    		
    	}
    	
    }
    /**
     * @return the console that can be asked for commands.
     */
    public Console getConsole(){
        return this.CONSOLE;
    }
    
}

