package terminal.adventure.game.commands;

import terminal.adventure.game.Console;

public class CommandHelp extends Command {

    @Override
    public void execute(String[] args, Console console) {

        System.out.println("Available commands:");
        for (String name : console.getCommands().keySet()) {

            Command cmd = console.getCommands().get(name);

            String description = "";
            if (cmd != null) {
                description = cmd.help();
            }

            System.out.println(" - " + name + " : " + description);
        }
    }

    @Override
    public String help() {
        return "\n Usage : \n HELP";
    }
}