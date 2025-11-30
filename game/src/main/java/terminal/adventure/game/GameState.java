package terminal.adventure.game;

import java.util.LinkedList;
import java.util.List;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.PlayerController;

public class GameState {
    
    private List<? extends Controller> controllers;
    private Location map;
    
    public GameState(PlayerController player, Location map){
        this.controllers = new LinkedList<>();
        this.controllers.add(player);
        this.map = null;
    }
}
