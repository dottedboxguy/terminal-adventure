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
    public void cast(Actor actor, Object target) {
        if (target instanceof LockedExit) {
            ((LockedExit)target).forcedUnlock();
        } else {
            System.out.println("You've cast such a great spell...   but nothing happened.");
        }
    }
}
