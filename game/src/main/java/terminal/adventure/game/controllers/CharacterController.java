package terminal.adventure.game.controllers;

public abstract class CharacterController {

    protected Character character;

    public CharacterController(Character character) {
        this.character = character;
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
