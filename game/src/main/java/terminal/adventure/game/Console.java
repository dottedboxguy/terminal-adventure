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
        input = input.trim();
        if (input.isEmpty()) return;

        String[] parts = input.split("\\s+", 2);
        
        String commandName = parts[0].toUpperCase();
        Command cmd = commands.get(commandName);
        
        if (cmd == null) {
            System.out.println("Unknown command. Type 'HELP'.");
            return;
        }

        String[] args;
        if (parts.length == 1) {
            args = new String[0];
        } else {
            
            args = parts[1].split("\\s*,\\s*");
            
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].trim();
            }
        }

        cmd.execute(args, this);
    }

    public PlayerController getPlayer(){
        return this.player;
    }

    public void print(String message){
        System.out.println(message);
    }
}