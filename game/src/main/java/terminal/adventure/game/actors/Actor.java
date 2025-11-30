package terminal.adventure.game.actors;

import exceptions.noPossibilitesException;
import exceptions.tooManyPossibilitesException;
import terminal.adventure.game.Location;
import terminal.adventure.game.Lookable;
import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.items.Item;


public abstract class Actor implements Lookable{
    
    public final String NAME;
    public final String DESCRIPTION;
    protected Inventory inventory;
    protected Equipment equipment;
    protected Stats baseStats;
    private Location currentLocation;
    
    
    public Actor(String name, String description) {
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
    public Location getCurrentLocation(){
        return this.currentLocation;
    }
    
    public Stats getBaseStats(){
    	return this.baseStats;
    }
    
    public void takeAttack(int attackPower) {
    	int currentHealth = this.getBaseStats().getCurrentHealth();
    	this.getBaseStats().setCurrentHealth(currentHealth - attackPower);
    	
    	if (this.isDead()) {
    		this.die();
    	}
    
    }
    
    public void die() {
    	System.out.println(this.NAME+" died like a loser.");
    }

    public boolean isDead() {
    	return this.getBaseStats().getCurrentHealth() <= 0;
    }
    
    
    @Override
    public String getDescription() {
        return this.DESCRIPTION;
    }

}
