package terminal.adventure.game;
import java.util.HashMap;
import java.util.Map;

import terminal.adventure.game.commands.Command;
import terminal.adventure.game.commands.CommandGo;
import terminal.adventure.game.commands.CommandHelp;
import terminal.adventure.game.commands.CommandLook;
import terminal.adventure.game.commands.CommandQuit;
import terminal.adventure.game.commands.CommandTake;
import terminal.adventure.game.commands.CommandUse;
import terminal.adventure.game.controllers.PlayerController;

public class Console{

    private final Map<String, Command> commands = new HashMap<>();
    private PlayerController player;

    public Console(PlayerController player){
        this.player = player;
        registerCommands();
    }

    private void registerCommands() {
        commands.put("GO", new CommandGo());
        commands.put("QUIT", new CommandQuit());
        commands.put("HELP", new CommandHelp());
        commands.put("LOOK", new CommandLook());
        commands.put("TAKE", new CommandTake());
        commands.put("USE", new CommandUse());
    }

    
    public Map<String, Command> getCommands() {
        return this.commands;
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
            System.out.println("Unknown command. Type 'HELP'.");
            return;
        }

        // Extract the arguments (all tokens except the first one)
        String[] args = new String[tokens.length - 1];
        for (int i = 1; i < tokens.length; i++) {
            args[i - 1] = tokens[i];
        }

        cmd.execute(args, this);
    }

    public PlayerController getPlayer(){
        return this.player;
    }
}