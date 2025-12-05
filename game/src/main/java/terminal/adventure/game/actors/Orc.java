package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.GlovesSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

public class Orc extends Actor{
    
    public Orc(String name, String description){
        super(name, description);
        this.baseStats.setMaxHealth(250);
        this.baseStats.setCurrentHealth(250);
        this.baseStats.setArmor(10);
        this.baseStats.setStrength(20);
    }
    
    @Override
    public List<Slot> makeSlots() {
        List<Slot> slots = new ArrayList<>();
        slots.add(new BootsSlot());
        slots.add(new GlovesSlot());
        slots.add(new HeadSlot());
        slots.add(new TorsoSlot());
        return slots;
    }
}