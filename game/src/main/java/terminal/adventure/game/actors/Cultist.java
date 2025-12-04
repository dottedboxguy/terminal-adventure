package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.items.BootsEquipment;
import terminal.adventure.game.inventory.items.HeadEquipment;
import terminal.adventure.game.inventory.items.TorsoEquipment;
import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

public class Cultist extends Actor{
    
    public Cultist(String name){
        super(name, "A silouhette wrapped in red robes. You cannot see their face, but you feel their stare.");
        this.baseStats.setMaxHealth(50);
        this.baseStats.setCurrentHealth(50);
        this.baseStats.setStrength(5);
        
        Stats s = new Stats();
        s.setArmor(2);
        s.setSpeed(1);
        this.equipment.equipItem(new BootsEquipment("Cultist Boots", "Boots worn by low rank blood cultists, they're worn out", s ), null);
        
        s = new Stats();
        s.setArmor(1);
        this.equipment.equipItem(new HeadEquipment("Cultist Hood", "Red hood worn by blood cultists, it's hard to see the face of the wearer", s ), null);
        
        s = new Stats();
        s.setArmor(3);
        this.equipment.equipItem(new TorsoEquipment("Cultist Robe", "Red robe worn by blood cultist, its golden engravings unsettle you", s ), null);
    }

    @Override
    public List<Slot> makeSlots(){
        List<Slot> slots = new ArrayList<>();
        slots.add(new BootsSlot());
        slots.add(new HeadSlot());
        slots.add(new TorsoSlot());
        return slots;
    }
}