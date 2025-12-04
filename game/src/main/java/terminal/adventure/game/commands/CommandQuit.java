package terminal.adventure.game.commands;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Console;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.controllers.PlayerController;

public class CommandQuit extends Command {

    public CommandQuit(String[] args) {
        super(args, true); 
    }

    @Override
    public String execute(Actor actor) throws invalidInputException {
        
        Console console = ((PlayerController) actor.getController()).getConsole();
        console.print("Leaving the game...");
        System.exit(0);
        
        return ""; // never read but must be there
    }

    @Override
    public String help() {
        return "QUIT -> quits the game.";
    }
}