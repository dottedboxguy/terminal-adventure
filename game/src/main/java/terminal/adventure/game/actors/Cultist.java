package terminal.adventure.game.actors;

import java.util.List;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.items.BootsEquipment;
import terminal.adventure.game.inventory.items.HeadEquipment;
import terminal.adventure.game.inventory.items.TorsoEquipment;
import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

/**
 * A cultist enemy wearing red robes and cultist gear.
 * Cultists have specific armor slots: boots, head, and torso.
 */
public class Cultist extends Actor {
    
    /**
     * Constructs a new Cultist with the given name.
     * Initializes the cultist's stats and equips cultist-specific gear.
     * 
     * @param name The name of the cultist
     */
    public Cultist(String name) {
        super(name, "A silouhette wrapped in red robes. You cannot see their face, but you feel their stare.");
        
        // Initialize cultist-specific stats
        this.baseStats.setMaxHealth(50);
        this.baseStats.setCurrentHealth(50);
        this.baseStats.setStrength(5);
        
        // Equip cultist-specific gear
        equipCultistGear();
    }
    
    /**
     * Equips the cultist with their standard gear:
     * - Cultist Boots (armor +2, speed +1)
     * - Cultist Hood (armor +1)
     * - Cultist Robe (armor +3)
     */
    private void equipCultistGear() {
        Stats bootsStats = new Stats();
        bootsStats.setArmor(2);
        bootsStats.setSpeed(1);
        this.equipment.equipItem(
            new BootsEquipment(
                "Cultist Boots", 
                "Boots worn by low rank blood cultists, they're worn out", 
                bootsStats
            ), 
            null
        );
        
        Stats hoodStats = new Stats();
        hoodStats.setArmor(1);
        this.equipment.equipItem(
            new HeadEquipment(
                "Cultist Hood", 
                "Red hood worn by blood cultists, it's hard to see the face of the wearer", 
                hoodStats
            ), 
            null
        );
        
        Stats robeStats = new Stats();
        robeStats.setArmor(3);
        this.equipment.equipItem(
            new TorsoEquipment(
                "Cultist Robe", 
                "Red robe worn by blood cultist, its golden engravings unsettle you", 
                robeStats
            ), 
            null
        );
    }
    
    /**
     * Customizes the slots available to this cultist.
     * Cultists can equip items in boots, head, and torso slots.
     * 
     * @param slots The list of slots to customize. Subclasses should add 
     *              their specific slots to this list.
     */
    @Override
    protected void customizeSlots(List<Slot> slots) {
        slots.add(new BootsSlot());
        slots.add(new HeadSlot());
        slots.add(new TorsoSlot());
    }
    
}