package terminal.adventure.game.commands;

import java.util.function.Function;

import terminal.adventure.game.Console;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.controllers.PlayerController;

public class CommandHelp extends Command {

    public CommandHelp(String[] args){
		super(args, false);
	}

    @Override
    public String execute(Actor actor) {
        
        String res = "Available commands:\n";
        Console console = ((PlayerController)actor.getController()).getConsole();
        for (String name : console.getCommands().keySet()) {
            Function<String[], Command> command = console.getCommands().get(name);
            Command cmd = command.apply(null);

            String description = "";
            if (cmd != null) {
                description = cmd.help();
            }
            res += (" - " + name + " : " + description + "\n");
        }
        return res;
    }

    @Override
    public String help() {
        return "\n HELP -> print all the commands you can use.";
    }

    @Override
    public boolean isTerminal(){
        return this.isTerminal;
    }
}