package terminal.adventure.game.commands;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Console;
import terminal.adventure.game.actors.Actor;

public class CommandQuit extends Command {

	
	public CommandQuit(String[] args){
		super(args,true);
	}
	
    @Override
    public String execute(Actor actor) {
        System.out.println("Leaving the game ...");
        System.exit(0);
        return "Bye !";
    }

    @Override
    public String help() {
        return "\n Usage : \n QUIT";
    }
    
}
