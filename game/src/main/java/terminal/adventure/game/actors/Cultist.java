package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.items.BootsEquipment;
import terminal.adventure.game.inventory.items.HeadEquipment;
import terminal.adventure.game.inventory.items.TorsoEquipment;
import terminal.adventure.game.inventory.slots.BootsSlot;
import terminal.adventure.game.inventory.slots.GlovesSlot;
import terminal.adventure.game.inventory.slots.HeadSlot;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.inventory.slots.TorsoSlot;

public class Cultist extends Actor{
    
    public Cultist(String name){
        super(name, "A silouhette wrapped in red robes, you cannot see its face, but you feel its stare.");
        this.baseStats.setMaxHealth(50);
        this.baseStats.setCurrentHealth(50);
        this.baseStats.setStrength(5);
        
        List<Slot> slotList = new ArrayList<>();
        
        slotList.add(new BootsSlot());
        slotList.add(new HeadSlot());
        slotList.add(new TorsoSlot());
        
        Stats s = new Stats();
        s.setArmor(2);
        slotList.get(0).equip(new BootsEquipment("Cultist Boots", s ));
        
        s = new Stats();
        s.setArmor(1);
        slotList.get(1).equip(new HeadEquipment("Cultist Hood", s ));
        
        s = new Stats();
        s.setArmor(3);
        slotList.get(2).equip(new TorsoEquipment("Cultist Robe", s ));
        
        this.equipment = new Equipment(slotList);
    }
}