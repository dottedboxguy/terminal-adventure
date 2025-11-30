package terminal.adventure.game.commands;

import terminal.adventure.game.Console;

public class CommandHelp implements Command {

    @Override
    public void execute(String[] args, Console console) {
        console.help();
    }

    @Override
    public String help() {
        return "\n Usage : \n HELP";
    }
}