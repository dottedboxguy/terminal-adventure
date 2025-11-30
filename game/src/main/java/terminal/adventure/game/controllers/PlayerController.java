package terminal.adventure.game.controllers;
import terminal.adventure.game.actors.Actor;

public abstract class PlayerController {

    protected Actor actor;

    public PlayerController(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return this.actor;
    }

    // Basic universal actions:
    public void attack(Actor actor) {
        System.out.println(actor.getName() + " attacks " + actor.getName());
        actor.takeDamage(actor.getAttackPower());
    }

    // Behavior defined per type:
    public abstract void takeTurn(GameState game, PlayerController playerController);
}
