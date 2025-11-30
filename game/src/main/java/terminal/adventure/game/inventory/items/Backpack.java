package terminal.adventure.game.inventory.items;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Inventory;

public class Backpack extends Item{

    public Inventory inventory;

    public Backpack(String name){
        super(name, new Stats());
        this.inventory = new Inventory();
    }
    
}
