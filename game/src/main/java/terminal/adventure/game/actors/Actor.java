package terminal.adventure.game.actors;

import terminal.adventure.exceptions.noPossibilitesException;
import terminal.adventure.exceptions.tooManyPossibilitesException;
import terminal.adventure.game.Fight;
import terminal.adventure.game.Location;
import terminal.adventure.game.Lookable;
import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
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
    
    private Controller controller;
    private Fight currentFight = null;
    
    public Actor(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
        this.inventory = new Inventory();
        this.baseStats = new Stats();
    }

    public void setController(Controller c) {

    	// Disconnecting old controller from this actor
    	if (this.controller != null) {
    		this.controller.unbindActor();
    	}

    	this.controller = c;

    	// If the new Controller's actor isn't already this one, set it.
    	if (this.controller.getActor() != this) {
    		c.bindActor(this);    		
    	}
    	
    	
    }
    
    public void clearController() {
    	if (this.controller != null) {
    		Controller temp = this.controller;
    		
    		this.controller = null;
    		temp.unbindActor();
    	}
    	
    }
    
    
    public void equipItem(Item item) throws tooManyPossibilitesException {
    	
    	try {
    		this.equipment.equip(item);
    	} catch ( noPossibilitesException e ) {
    		System.out.println(NAME+" miserably fails to equip "+item.getName()+".");
    	}

    }

    
    public void takeAttack(int attackPower) {
    	System.out.println("DEBUG Actor.takeAttack : Ouch ! I'm "+this.NAME+" and I have "+this.getBaseStats().getCurrentHealth()+" HP remaing");
    	
    	int currentHealth = this.getBaseStats().getCurrentHealth();
    	this.getBaseStats().setCurrentHealth(currentHealth - attackPower);
    	
    	if (this.isDead()) {
    		this.die();
    	}
    
    }
    
    public void die() {
    	// Actions to perform at death (loot drop, events, etc)
    	this.controller.die();
    }
    
    public boolean isDead() {
    	return this.getBaseStats().getCurrentHealth() <= 0;
    }
    
    public void attack(Actor target) {
    	target.takeAttack(this.getBaseStats().getStrength());
    }

    
    //-------------- Basic Getters ------------------
    
    public Inventory getInventory(){ return this.inventory; }
    
    @Override
    public String getDescription() {
        return this.DESCRIPTION;
    }


	public Controller getController() {
		return this.controller;
	}
	
	public String getName() { return this.NAME; }
	
	
	public Location getCurrentLocation(){
		return this.currentLocation;
	}
	
	public Stats getTotalStats() {
		// TO DO
		return null;
	}
	
	public Stats getBaseStats(){
		return this.baseStats;
	}
	
	//-------------- Fight-related methods -------------
	
	public void enterFight(Fight f ) {
		this.leaveFight();
		this.currentFight = f;
		f.addFighter(this);
	}
	
	public void leaveFight() {
		if (this.currentFight != null) {
			this.currentFight.removeFighter(this);
			this.currentFight = null;
		}
	}
	
	public Fight getFight() {
		return this.currentFight;
	}
	
}
