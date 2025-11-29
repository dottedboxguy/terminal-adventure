package terminal.adventure.game;
public class UnlockSpell extends Spell {

    public UnlockSpell() {
        super("Unlock", "Opens a magically sealed exit.");
    }

    @Override
    public void cast(GameState game, Player player, Object target) {
        if (target instanceof LockedExit exit) {
            exit.unlock();
            System.out.println("The lock glows and clicks open.");
        } else {
            System.out.println("The spell has no effect on this.");
        }
    }
}
