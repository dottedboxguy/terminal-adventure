package terminal.adventure.game.commands;

import terminal.adventure.game.Console;

public class CommandQuit extends Command {

    @Override
    public void execute(String[] args, Console console) {
        System.out.println("Leaving the game ...");
        System.exit(0);
    }

    @Override
    public String help() {
        return "\n Usage : \n QUIT";
    }
}