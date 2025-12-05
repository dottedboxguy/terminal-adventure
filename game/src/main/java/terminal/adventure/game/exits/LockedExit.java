package terminal.adventure.game.exits;

import terminal.adventure.game.Location;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.interactables.Interactable;
import terminal.adventure.game.inventory.items.Item;

public class LockedExit extends Exit implements Interactable{
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

    @Override
    /**
     * see {@link Interactable}
     */
    public String action(Actor actor) {
        return "The door seems locked.";
    }

    /**
     * see {@link Interactable}
     * Unlocks the exit using an item.
     * @param item used to try unlocking the exit.
     * @return The fail/success message.
     */
    @Override
    public String actionWithItem(Actor actor, Item item) {
        if (item == this.requiredKey){
            this.isLocked = false;
            return "You managed to open the door !";
        }
        return "That doesn't quite fit";
    }    
    
    /**
     * Unlocks the door by bypassing the key Item.
     */
    public void forcedUnlock(){
        this.isLocked = false;
    }
    
}
