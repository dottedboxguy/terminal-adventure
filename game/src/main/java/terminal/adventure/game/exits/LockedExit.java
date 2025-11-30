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
    public boolean canCross(Character character) {
        return !(this.isLocked);
    }

    @Override
    public String getFailMessage() {
        return "The door is locked. You need the " + this.requiredKey + ".";
    }

    public String unlock(Item item){
        if (item == this.requiredKey){
            this.isLocked = false;
            return "This exit is unlocked, you can now cross it.";
        }
        return "It seems like this doesn't fit";
    }

    public String forcedUnlock(){
        this.isLocked = false;
        return "WOOOAAHH!!! the lock got destroyed \n You can now pass freely.";
    }
}
