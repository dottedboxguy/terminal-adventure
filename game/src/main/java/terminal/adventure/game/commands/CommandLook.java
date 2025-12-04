package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Console;
import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;

public class CommandLook extends Command {

	
	public CommandLook(String[] args) {
		super(args, false);
	}

	
    @Override
    public String execute(Actor actor)  {
          if (args.length == 0) {
            return actor.getCurrentLocation().look();
        }
        String result = "";
        for (String arg : args) {
            
            List<Actor> actors = actor.getCurrentLocation().getActorByName(arg);
            for (Actor a : actors) {
                result += a.look() + "\n";
            }
            
            List<Exit> exits = actor.getCurrentLocation().getExitByName(arg);
            for (Exit exit : exits) {
                result += exit.look() + "\n";
            }
            
            if (actors.isEmpty() && exits.isEmpty()) {
                return "There is no" + arg + " in here.";
            }
        }
        return result;
    }

    @Override
    public String help() {
        return "\nLOOK <Argument> -> ";
    }

	@Override
	public String execute(Actor actor) throws invalidInputException {
		// TODO Auto-generated method stub
		return null;
	}
}