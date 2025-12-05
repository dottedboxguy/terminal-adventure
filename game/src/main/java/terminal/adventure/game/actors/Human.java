package terminal.adventure.game.actors;

import java.util.List;

import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.GlovesSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

/**
 * Abstract base class for human characters with standard equipment slots.
 * Humans have boots, gloves, head, and torso slots.
 * This class serves as a base for player characters and human NPCs.
 */
public class Human extends Actor {

    /**
     * Constructs a new Human with the given name and description.
     * Initializes the human's base stats to standard values.
     * 
     * @param name The name of the human
     * @param description A textual description of the human
     */
    public Human(String name, String description) {
        super(name, description);
        
        // Initialize human-specific stats
        this.baseStats.setMaxHealth(100);
        this.baseStats.setCurrentHealth(100);
        this.baseStats.setStrength(10);
    }

    /**
     * Customizes the slots available to this human.
     * Humans can equip items in boots, gloves, head, and torso slots.
     * 
     * @param slots The list of slots to customize. Subclasses should add 
     *              their specific slots to this list.
     */
    @Override
    public void customizeSlots(List<terminal.adventure.game.inventory.slots.Slot> slots) {
        slots.add(new BootsSlot());
        slots.add(new GlovesSlot());
        slots.add(new HeadSlot());
        slots.add(new TorsoSlot());
    }
}