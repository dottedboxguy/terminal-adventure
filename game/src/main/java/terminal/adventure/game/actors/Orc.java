package terminal.adventure.game.actors;

import java.util.List;

import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.GlovesSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

/**
 * An orc enemy - a large, strong, and heavily armored creature.
 * Orcs have high health, armor, and strength stats.
 * They can equip items in boots, gloves, head, and torso slots.
 */
public class Orc extends Actor {
    
    /**
     * Constructs a new Orc with the given name and description.
     * Initializes the orc's base stats with high values for health,
     * armor, and strength.
     * 
     * @param name The name of the orc
     * @param description A textual description of the orc
     */
    public Orc(String name, String description) {
        super(name, description);
        
        // Initialize orc-specific stats - high health, armor, and strength
        this.baseStats.setMaxHealth(250);
        this.baseStats.setCurrentHealth(250);
        this.baseStats.setArmor(10);
        this.baseStats.setStrength(20);
    }
    
    /**
     * Customizes the slots available to this orc.
     * Orcs can equip items in boots, gloves, head, and torso slots.
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