package terminal.adventure.game.commands;

import terminal.adventure.game.Console;

public class CommandGo implements Command {

    @Override
    public void execute(String[] args, Console console) {
        
    }

    @Override
    public String help() {
        return "\n Usage : \n GO \t <Location>";
    }
}