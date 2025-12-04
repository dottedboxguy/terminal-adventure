package terminal.adventure.game.spells;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.HiddenExit;

public class VisionSpell extends Spell {

    public VisionSpell() {
        super("Greater Vision", "Lets you see the unseen");
    }

    @Override
    /**
     * see {@link Spell}
     */
    public void cast(Actor actor, Object target) {
        if (target instanceof HiddenExit) {
            ((HiddenExit)target).unveil();
        }
        else {
            System.out.println("You've cast such a great spell...   but nothing happened.");
        }
    }
}
