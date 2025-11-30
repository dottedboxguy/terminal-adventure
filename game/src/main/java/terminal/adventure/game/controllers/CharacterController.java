package terminal.adventure.game.controllers;

import terminal.adventure.game.characters.Character;


public abstract class CharacterController extends Controller {

    public CharacterController(Character character) {
    	super(character);
    }

    // Basic universal actions:
    public void attack(CharacterController target) {
    	//TODO
    }

    // Behavior defined per type:
    public abstract void takeTurn(GameState game, PlayerController playerController);
    
    
}
