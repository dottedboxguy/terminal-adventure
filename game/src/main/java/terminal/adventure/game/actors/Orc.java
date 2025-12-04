package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.inventory.slots.Slot;

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
        return slots;
    }
}