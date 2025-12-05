package terminal.adventure.game.spells;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.LockedExit;

public class UnlockSpell extends Spell {

    public UnlockSpell() {
        super("Unlock", "Opens a magically sealed exit.");
    }

    @Override
    /**
     * see {@link Spell}
     */
    public String cast(Actor actor, Object target) {
        if (target instanceof LockedExit) {
            return ((LockedExit)target).action(actor);
        } else {
            return "You've cast such a great spell...   but nothing happened.";
        }
    }
}
