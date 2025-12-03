package terminal.adventure.game.actors;

import java.util.List;
import java.util.ArrayList;

import terminal.adventure.game.Fight;
import terminal.adventure.game.Location;
import terminal.adventure.game.Lookable;
import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.inventory.Equipment;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;


public abstract class Actor implements Lookable{
    
    public final String NAME;
    public final String DESCRIPTION;
    protected Equipment equipment;
    protected Stats baseStats;
    private Location currentLocation;
    
    private Controller controller;
    private Fight currentFight = null;
    
    
    public Actor(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
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

    public boolean equip(Item item, Controller controller) {
    	return this.equipment.equipItem(item, controller);
    }
    
    
    /**
     * Looks for the item by name in the actor's storages, and in the Location,
     * and tries to equip it.
     * @param itemName
     * @param controller
     * @return
     */
    boolean equipItem(String itemName, Controller controller) {
    	
    	List<Item> candidates;
    	
    	List<Storage> sources = this.equipment.getAllStorages();
    	
    	if (this.getCurrentLocation() != null) {    		
    		sources.add(this.getCurrentLocation());
    	}
    	
    	
    	
    	for( Storage s : sources ) {
    		
    		candidates = s.searchItems(itemName);
    		
    		if (candidates.isEmpty()) {
    			continue;
    		}
    			
			Item foundItem = candidates.get(0);
			
			if( this.equip( foundItem, controller)) {
				
				s.removeItem(foundItem);
				
				return true;
			} else {
				
				return false;
			}
    	}
    	
    	return false;
    	
    }
    
    
    public void takeAttack(int attackPower) {
    	
    	int currentHealth = this.getBaseStats().getCurrentHealth();
    	this.getBaseStats().setCurrentHealth(currentHealth - attackPower);
    	
    	if (this.isDead()) {
    		System.out.println("DEBUG Actor.takeAttack : OOOFFFF Im "+ this.NAME+ " and I dramatically died.");
    		this.die();
    	} else {
    		
    		System.out.println("DEBUG Actor.takeAttack : Ouch ! I'm "+this.NAME+" and I have "+this.getBaseStats().getCurrentHealth()+" HP remaing");
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

    public boolean takeItem(Item item) {
    	Storage s = this.getFirstStorage();
    	if (s != null) {
    		s.addItem(item);
    		return true;
    	}
    	return false;
    }
    
    
    //-------------- Basic Getters / Setters ------------------
    
    
    public boolean hasBackpack() {
    	return this.equipment.containsBackpack();
    }
    
    public Storage getFirstStorage(){ 
    	return this.equipment.getfirstStorage();
    }
    
    
    
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
	
	public void setLocation(Location loc) {
		this.currentLocation = loc;
	}
	
	public Stats getTotalStats() {
		return this.baseStats.statsSum(this.equipment.totalStats());
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

	//-------------- Move methods -------------

	public String Move(Location destination){
		if (destination == null) {
        	return "That location doesn't exist!";
    	}
		Exit exitToCross = null;

		for (Exit exit : this.currentLocation.getVisibleExits().values()) {
			if (exit.getDestination().equals(destination)){
				exitToCross = exit;
			}
		}

		if (exitToCross == null){
			return "There are no visible exits leading to " + destination;
		}

		if (exitToCross.canCross()){
			this.currentLocation.removeActor(this); 
			this.currentLocation = destination;
			this.currentLocation.addActor(this);
			return exitToCross.getMessage();
		}
		else{
			return exitToCross.getMessage();
		}

	}

	public String go(Location target) {
		//TODO
		return "I'm going to "+target.getName()+" !";
	}
}
