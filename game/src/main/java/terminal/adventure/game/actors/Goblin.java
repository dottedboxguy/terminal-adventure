package terminal.adventure.game.actors;

import java.util.List;

import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.GlovesSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

/**
 * A goblin enemy - a small, green, filthy creature.
 * Goblins can equip items in boots, gloves, head, and torso slots.
 */
public class Goblin extends Actor {
    
    /**
     * Constructs a new Goblin with the given name.
     * Initializes the goblin's base stats.
     * 
     * @param name The name of the goblin
     */
    public Goblin(String name) {
        super(name, "A green filthy creature. Ew.");
        
        // Initialize goblin-specific stats
        this.baseStats.setMaxHealth(40);
        this.baseStats.setCurrentHealth(40);
        this.baseStats.setStrength(3);
        
        // Note: The equipment is already initialized by the parent Actor class
        // with the slots defined in customizeSlots() plus a BackpackSlot
    }
    
    /**
     * Customizes the slots available to this goblin.
     * Goblins can equip items in boots, gloves, head, and torso slots.
     * 
     * @param slots The list of slots to customize. Subclasses should add 
     *              their specific slots to this list.
     */
    @Override
    protected void customizeSlots(List<terminal.adventure.game.inventory.slots.Slot> slots) {
        slots.add(new BootsSlot());
        slots.add(new GlovesSlot());
        slots.add(new HeadSlot());
        slots.add(new TorsoSlot());
    }
}