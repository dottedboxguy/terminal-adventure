package terminal.adventure.game.controllers;
import java.util.List;

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
}
