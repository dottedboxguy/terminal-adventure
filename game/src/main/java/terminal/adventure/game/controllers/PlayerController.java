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
    	String candidatesShow = "Several equippement slot possibles :\n";
    	int nb = 0;
    	
    	for (String candidate : candidatesNames) {
    		if (candidate == null ) {
    			candidatesShow+=nb+"- <empty slot>\n";
    		} else {
    			candidatesShow+=nb+"- "+candidate;
    		}    			
    		nb++;
    	}
    	
    	this.getConsole().print(candidatesShow);;
    	
    	int response = -1;
    	
    	try {
    		response = Integer.parseInt(this.getConsole().getFreeInput());
    	}catch(NumberFormatException e) {/* Not an Integer.*/}
    	
    	return response;
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

