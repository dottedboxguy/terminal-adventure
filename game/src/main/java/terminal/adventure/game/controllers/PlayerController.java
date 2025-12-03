package terminal.adventure.game.controllers;
import java.io.Console;
import java.util.List;

import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;

public class PlayerController extends Controller{

    public PlayerController(Faction faction) {
    	super(faction);
    }

    // Basic universal actions:
    public void attack(Actor actor) {
    	//
    }

    // Behavior defined per type:
    // public void takeTurn(/*GameState game,*/ PlayerController playerController);

    @Override
    public int equipChooseSlot(List<String> candidatesNames) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void play() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // ---------------------------------------
    // ##  Methods IN GAME chat...
    // ---------------------------------------

    public void movePlayer(Actor actor, Location newLocation){
        String message = actor.Move(newLocation);
        this.console.print(message);
    }
}
