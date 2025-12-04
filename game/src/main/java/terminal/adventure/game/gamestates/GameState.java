package terminal.adventure.game.gamestates;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Queue;

import terminal.adventure.game.Location;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.PlayerController;

public abstract class GameState {
    
    protected final Queue<Controller> controllers;
    protected final List<Location> map;
    
    public GameState(Queue<Controller> controllers, List<Location> map){
        this.controllers = controllers;
        this.map = map;
    }

    /**
     * Starts the game loop
     */
    public void play(){
        boolean winCondition = false;
        boolean loseCondition = false;
        while (!winCondition && !loseCondition ) { // "main" loop
            Controller c = controllers.poll();
            
            if (c.isDead()) {
                if (c instanceof PlayerController) loseCondition = true;
                continue;
            }
        
            System.out.println("It's "+ c.getActor().NAME+" turn");

            c.playTurn();

            controllers.add(c);
        }
    }
    
    /**
     * Saves the game in a file
     * @param gameState the game to save
     * @param filePath a path where the file will be stored.
     * @throws FileNotFoundException
     * @throws IOException if the creation/writing of the file fails.
     */
    public static void saveInFile(GameState gameState , String filePath) throws FileNotFoundException, IOException {
	
    	ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("save.tas"));
    	out.writeObject(gameState);
    	
    	out.close();
    }
    
    /**
     * Loads a game from a file.
     * @param filePath the path of the save file.
     * @return the loaded gamestate
     * @throws FileNotFoundException if the file is not found.
     * @throws IOException if file reading fails
     * @throws ClassNotFoundException if the save file is invalid.
     */
    public static GameState loadFromFile(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
    	
    	ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.tas"));
    	GameState res = (GameState) input.readObject();
    	
    	input.close();
    	
    	return res;
    }
    
}
