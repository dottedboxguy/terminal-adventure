package terminal.adventure.game.commands;

import terminal.adventure.game.actors.Actor;

public class CommandHelp extends Command {

    @Override
    public String execute(Actor actor, String[] args) {
        String s = "";
        s += "Available commands:\n";
        for (String name : actor.getController().getCommands().keySet()) {

            Command cmd = actor.getController().getCommands().get(name);

            String description = "";
            if (cmd != null) {
                description = cmd.help();
            }

            System.out.println(" - " + name + " : " + description);
        }
    }

    @Override
    public String help() {
        return "\nUsage : HELP";
    }
}