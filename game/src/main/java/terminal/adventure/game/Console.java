package terminal.adventure.game;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import terminal.adventure.game.commands.Command;
import terminal.adventure.game.commands.CommandAttack;
import terminal.adventure.game.commands.CommandCast;
import terminal.adventure.game.commands.CommandEquip;
import terminal.adventure.game.commands.CommandGo;
import terminal.adventure.game.commands.CommandHelp;
import terminal.adventure.game.commands.CommandLook;
import terminal.adventure.game.commands.CommandQuit;
import terminal.adventure.game.commands.CommandTake;
import terminal.adventure.game.commands.CommandUse;
import terminal.adventure.game.controllers.PlayerController;

public class Console{

    private PlayerController player;
    private final PrintStream printStream;
    private final Scanner inputScanner;
    private final Map<String, Function<String[], Command>> commands = new HashMap<>();
    
    String latestEntry = ""; 
    
    public Console(){
        
        printStream = System.out;
        inputScanner = new Scanner(System.in);
        registerCommands();
        
    }

    private void registerCommands() {
    	commands.put("GO"  , CommandGo::new );
        commands.put("QUIT", CommandQuit::new);
        commands.put("HELP", CommandHelp::new);
        commands.put("LOOK", CommandLook::new);
        commands.put("TAKE", CommandTake::new);
        commands.put("USE" , CommandUse::new);
        commands.put("CAST", CommandCast::new);
        commands.put("EQUIP", CommandEquip::new);
        commands.put("ATTACK", CommandAttack::new);
    }

    public Map<String, Function<String[], Command>> getCommands() {
    	return this.commands;
    }

    /**
     * This method is called by a Controller.
     * @return the command to run
     */
    public Command getAction(){

    	return getCommand();
    }
    
    /**
    * Parses and returns a console command entered by the user.
    * 
    * If the command does not exist, an error message is displayed.
    */
    private Command getCommand() {
    	printStream.print("\n");
    	String input = this.inputScanner.nextLine();
    	printStream.print("\n");
    	
    	if (input.equals("r")) {
    		printStream.print(latestEntry);
    		input = latestEntry;
    	} else {
    		latestEntry = input;    		
    	}
    	
    	// ----------- Command identification ---------
    	
        input = input.trim();
        if (input.isEmpty()) return null;

        String[] parts = input.split("\\s+", 2);
        
        String commandName = parts[0].toUpperCase();
        Function<String[], Command> cmd = commands.get(commandName);
        if (cmd == null) {
            System.out.println("Unknown command. Type 'HELP'.");
            return null;
        }
        

        //------------ Argument Parsing -------------
        
        String[] args;
        if (parts.length == 1) {
            args = new String[0];
        } else {
            
            args = parts[1].split("\\s*,\\s*");
            
            for (int i = 0; i < args.length; i++) {
                args[i] = args[i].trim();
            }
        }

        Command command = cmd.apply(args);
        
        return command;
    }
    public void quit(){
        this.print("Leaving the game...");
        System.exit(0);
    }
    public PlayerController getPlayer(){
        return this.player;
    }  
    
    public void print(String message){
        printStream.println(message);
    }
    
    public String getFreeInput() {
    	return inputScanner.nextLine();
    }
}