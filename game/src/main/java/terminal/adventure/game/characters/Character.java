package terminal.adventure.game.characters;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.items.Item;
public class Character {
    
    public final String NAME;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Stats baseStats;
    
    
    public Character(String name) {
        this.NAME = name;
        this.inventory = new Inventory();
        this.baseStats = new Stats();
    }

    public void equipItem(Item item) {
        this.equipment.equip(item);
    }

    // Getters
    public String getName() { return this.NAME; }
    
    public int getTotalStat(String name) {
        // TO DO
    }
}
