package terminal.adventure.game.commands;

import terminal.adventure.game.Console;

public class CommandLook implements Command {

    @Override
    public void execute(String[] args, Console console) {
        if (args.length == 0 ){

        }
        else {
            console
        }
    }

    @Override
    public String help() {
        return "\n Usage : \n LOOK \t ";
    }
}