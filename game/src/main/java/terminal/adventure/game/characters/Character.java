package terminal.adventure.game.characters;

import exceptions.noPossibilitesException;
import exceptions.tooManyPossibilitesException;
import terminal.adventure.game.Location;
import terminal.adventure.game.Lookable;
import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.items.Item;

public abstract class Character implements Lookable{
    
    public final String NAME;
    public final String DESCRIPTION;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Stats baseStats;
    private Location currentLocation;
    
    
    public Character(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
        this.inventory = new Inventory();
        this.baseStats = new Stats();
    }

    public void equipItem(Item item) throws noPossibilitesException, tooManyPossibilitesException {
        this.equipment.equip(item);
    }

    // Getters
    public String getName() { return this.NAME; }
    
    public int getTotalStat(String name) {
        // TO DO
    }
    public Location getCurrentLocation(){
        return this.currentLocation;
    }
    @Override
    public String getDescription() {
        return this.DESCRIPTION;
    }

}
