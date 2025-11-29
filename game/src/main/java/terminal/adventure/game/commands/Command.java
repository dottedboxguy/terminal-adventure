package terminal.adventure.game.commands;

import terminal.adventure.game.Console;

public interface Command {
    void execute(String[] args, Console console);
    String help();
}
