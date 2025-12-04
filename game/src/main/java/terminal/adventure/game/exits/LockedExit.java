package terminal.adventure.game.exits;

import terminal.adventure.game.Location;
import terminal.adventure.game.inventory.items.Item;

public class LockedExit extends Exit {
    private boolean isLocked = true;
    private final Item requiredKey;

    public LockedExit(Location destination, String name, Item requiredKey, String description) {
        super(destination, name, description);
        this.requiredKey = requiredKey;
    }

    @Override
    /**
     * see {@link Exit}
     */
    public boolean canCross() {
        return !(this.isLocked);
    }

    @Override
    /**
     * see {@link Exit}
     */
    public String getMessage() {
        return "The door is locked. You need the " + this.requiredKey + ".";
    }

    /**
     * Unlocks the exit using an item.
     * @param item used to try unlocking the exit.
     * @return The fail/success message.
     */
    public String unlock(Item item){
        if (item == this.requiredKey){
            this.isLocked = false;
            return "This exit is unlocked, you can now cross it.";
        }
        return "It seems like this doesn't fit";
    }

    /**
     * Unlocks the exit bypassing the need for a key item.
     * @return the message to print after forcing an Exit.
     */
    public String forcedUnlock(){
        this.isLocked = false;
        return "WOOOAAHH!!! the lock got destroyed \n You can now pass freely.";
    }
}
