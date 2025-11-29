package terminal.adventure.game;
import java.util.HashMap;
import java.util.Map;

import terminal.adventure.game.commands.*;


public class Console{
    private final Map<String, Command> commands = new HashMap<>();
    public Console(){
        registerCommands();
    }

    private void registerCommands() {
        commands.put("GO", new CommandGo());
        commands.put("QUIT", new CommandQuit());
        commands.put("HELP", new CommandHelp());
    }

    /**
    * Displays a list of all available console commands along with their descriptions.
    * If a command entry is found without an associated object an empty description is displayed.
    */
    public void help() {

        System.out.println("Available commands:");

        for (String name : commands.keySet()) {

            Command cmd = commands.get(name);

            String description = "";
            if (cmd != null) {
                description = cmd.help();
            }

            System.out.println(" - " + name + " : " + description);
        }
    }
    /**
    * Parses and executes a console command entered by the user.
    * 
    * If the command does not exist, an error message is displayed.
    *
    * @param input the raw user input typed in the console
    */
    public void runCommand(String input) {

        // Split the input into tokens (first word = command name, rest = arguments)
        String[] tokens = input.trim().split("\\s+"); // trim removes leading/trailing spaces; split separates on spaces
        if (tokens.length == 0) return;

        Command cmd = commands.get(tokens[0]); // returns null if the command does not exist
        if (cmd == null) {
            System.out.println("Unknown command. Type 'help'.");
            return;
        }

        // Extract the arguments (all tokens except the first one)
        String[] args = new String[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            args[i - 1] = tokens[i];
        }

        cmd.execute(args, this);
    }

}