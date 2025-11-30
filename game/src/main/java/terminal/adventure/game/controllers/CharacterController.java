package terminal.adventure.game.controllers;

import terminal.adventure.game.characters.Character;


public abstract class CharacterController extends Controller {

    public CharacterController(Character character) {
    	super(character);
    }

    public Character getCharacter() {
        return character;
    }

    // Basic universal actions:
    public void attack(CharacterController target) {
        System.out.println(character.getName() + " attacks " + target.getCharacter().getName());
        target.getCharacter().takeDamage(character.getAttackPower());
    }

    // Behavior defined per type:
    public abstract void takeTurn(GameState game, PlayerController playerController);
}
