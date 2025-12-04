package terminal.adventure.game.actors;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.inventory.slots.Slot;

public class Human extends Actor {

    public Human(String name, String description){
        super(name, description);
        this.baseStats.setMaxHealth(100);
        this.baseStats.setCurrentHealth(100);
        this.baseStats.setStrength(5);
    }

    @Override
    public List<Slot> makeSlots() {
        List<Slot> slots = new ArrayList<>();
        return slots;
    }

}