package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.GlovesSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

public class Goblin extends Actor{
    
    public Goblin(String name){
        super(name, "A green filthy creature. Ew.");
        this.baseStats.setMaxHealth(40);
        this.baseStats.setCurrentHealth(40);
        this.baseStats.setStrength(3);
        
        List<Slot> slotList = new ArrayList<>();
        
        slotList.add(new BootsSlot());
        slotList.add(new GlovesSlot());
        slotList.add(new HeadSlot());
        slotList.add(new TorsoSlot());
        
        this.equipment = new Equipment(slotList);
    }
}