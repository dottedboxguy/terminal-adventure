package terminal.adventure.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.PlayerController;

public class GameState {
    
    private List<Controller> controllers;
    private Location map;
    
    public GameState(PlayerController player, Location map){
        this.controllers = new LinkedList<>();
        this.controllers.add(player);
        this.map = map;
    }
    
    public static void saveInFile(GameState gameState , String filePath) throws FileNotFoundException, IOException {
	
    	ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("save.tas"));
    	out.writeObject(gameState);
    	
    	out.close();
    }
    

    public static GameState loadFromFile(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
    	
    	ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.tas"));
    	GameState res = (GameState) input.readObject();
    	
    	input.close();
    	
    	return res;
    }

    
    
    
}
