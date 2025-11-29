package terminal.adventure.game.controllers;
public class MobController extends CharacterController {

    public MobController(Mob mob) {
        super(mob);
    }

    @Override
    public void takeTurn(GameState game, PlayerController player) {

        if (!character.isAlive())
            return;

        // Simple AI: attack the player
        System.out.println(character.getName() + " makes a hostile move!");
        attack(player);

        if (!player.getCharacter().isAlive()) {
            System.out.println("You have been slain by " + character.getName() + "...");
        }
    }
}
