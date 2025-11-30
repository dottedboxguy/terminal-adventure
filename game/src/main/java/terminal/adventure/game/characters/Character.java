package terminal.adventure.game.characters;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.items.Item;

import exceptions.tooManyPossibilitesException;
import exceptions.noPossibilitesException;


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
    
}
