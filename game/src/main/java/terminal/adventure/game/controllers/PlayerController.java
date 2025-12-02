package terminal.adventure.game.controllers;
import terminal.adventure.game.actors.Actor;

public abstract class PlayerController extends Controller{

    protected Actor actor;

    public PlayerController(Faction faction) {
    	super(faction);
    }

    // Basic universal actions:
    public void attack(Actor actor) {
    	//
    }

    // Behavior defined per type:
    public abstract void takeTurn(/*GameState game,*/ PlayerController playerController);
}
