package terminal.adventure.game.characters;

import exceptions.noPossibilitesException;
import exceptions.tooManyPossibilitesException;
import terminal.adventure.game.Location;
import terminal.adventure.game.Lookable;
import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.items.Item;

import exceptions.tooManyPossibilitesException;
import exceptions.noPossibilitesException;


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

    public void equipItem(Item item) throws tooManyPossibilitesException {
    	
    	try {
    		this.equipment.equip(item);
    	} catch ( noPossibilitesException e ) {
    		System.out.println(NAME+" miserably fails to equip "+item.getName()+".");
    	}

    }

    // Getters
    public String getName() { return this.NAME; }
    
    public Stats getTotalStats() {
        // TO DO
    	return null;
    }
    
    public Stats getBaseStats(){
    	return this.baseStats;
    }
    
    public void takeAttack(int attackPower) {
    	// TODO
    }
    

    @Override
    public String getDescription() {
        return this.DESCRIPTION;
    }

}
