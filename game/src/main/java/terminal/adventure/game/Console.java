package terminal.adventure.game;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import terminal.adventure.game.commands.Command;
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
    
    public Console(){
        
        printStream = System.out;
        inputScanner = new Scanner(System.in);
        
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
    public Command getCommand() {
    	String input = this.inputScanner.nextLine();
    	
    	// ----------- Command identification ---------
    	
        input = input.trim();
        if (input.isEmpty()) return null;

        String[] parts = input.split("\\s+", 2);
        
        String commandName = parts[0].toUpperCase();
        
        Command cmd = commands.get(commandName);
        
        if (cmd == null) {
            System.out.println("Unknown command. Type 'HELP'.");
            return null;
        } else {
        	cmd = cmd.copy();
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

        System.out.println("DEBUT Console getCommand\n args : "+args+"CMD :"+cmd);
        cmd.setArgs(args);
        
        return cmd;
    }

    public PlayerController getPlayer(){
        return this.player;
    }  

    public void print(String message){
        printStream.println(message);
    }
}