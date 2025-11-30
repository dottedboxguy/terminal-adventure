package terminal.adventure.game.commands;

import terminal.adventure.game.Console;

public interface Command {
    public void execute(String[] args, Console console);
    public String help();
}
