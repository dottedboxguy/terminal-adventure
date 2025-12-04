package terminal.adventure.game.actors;

import java.util.List;

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
    
    /**
     * Sets the given controller to this actor.
     * Warning : will disconnect the current controller if any,
     * along with the controller's current actor.
     * @param c The new controller to bind.
     */
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
    
    /**
     * Resets this actor's controller.
     * will also resets the current controller's actor if present.
     */
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
     * @param itemName The name of the item to look for.
     * @param controller the controller to refer to if there is several eligible slots where it could be equipped.
     * @return if the equip is successful.
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
    
    
    /**
     * Allows the actor to receive an attack.
     * The effective damage dealt can be affected by this actor's armor or speed.
     * @param attackPower the initial amount of damage dealt.
     */
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
    
    /**
     * Called whenever the actor should die.
     * Tells the controller its actor is dead, which disconnects it.
     */
    public void die() {
    	// Actions to perform at death (loot drop, events, etc)
    	this.controller.die();
    }
    
    /**
     * @return If the actor's current Health is 0 or less.
     */
    public boolean isDead() {
    	return this.getBaseStats().getCurrentHealth() <= 0;
    }
    
    /**
     * Triggers an attack from this actor to the given one.
     * @param target the actor to attack.
     */
    public void attack(Actor target) {
    	target.takeAttack(this.getBaseStats().getStrength());
    }

    /**
     * Puts the given item in the first Storage found in the actor's Inventory.
     * @param item the item to add to the inventory.
     * @return if the addition was successful. (can fail if no Storage is equipped).
     */
    public boolean takeItem(Item item) {
    	Storage s = this.getFirstStorage();
    	if (s != null) {
    		s.addItem(item);
    		return true;
    	}
    	return false;
    }
    
    
    //-------------- Basic Getters / Setters ------------------
    
    /**
     * @return if this actor has at least one storage item it its equipment.
     */
    public boolean hasStorage() {
    	return this.equipment.containsStorage();
    }
    
    /**
     * @return returns the first storage item found in the inventory.
     */
    public Storage getFirstStorage(){ 
    	return this.equipment.getfirstStorage();
    }
	
    /**
     * @return this actor's name.
     */
	public String getName() { return this.NAME; }

	/**
	 * @return this actor's controller, null if none
	 */
	public Controller getController() {
		return this.controller;
	}
	
	/**
	 * @return this actor's current location, null if none
	 */
	public Location getCurrentLocation(){
		return this.currentLocation;
	}
	
	/**
	 * @param loc this new actor's location.
	 */
	public void setLocation(Location loc) {
		this.currentLocation = loc;
	}
	
	/**
	 * Adds up all of the equipped items's stats and this actor's base stats.
	 * @return the total sum of stats.
	 */
	public Stats getTotalStats() {
		return this.baseStats.statsSum(this.equipment.totalStats());
	}
	
	/**
	 * @return the base stats of the actor.
	 */
	public Stats getBaseStats(){
		return this.baseStats;
	}
	
	//-------------- Fight-related methods -------------
	
	/**
	 * Adds this actor to the specified fight and sets it
	 * as the current actor's fight attribute.
	 * @param f the fight to join
	 */
	public void enterFight(Fight f ) {
		this.leaveFight();
		this.currentFight = f;
		f.addFighter(this);
	}
	
	/**
	 * Removes this actor from the specified fight and
	 * resets the current actor's fight attribute
	 */
	public void leaveFight() {
		if (this.currentFight != null) {
			this.currentFight.removeFighter(this);
			this.currentFight = null;
		}
	}
	
	/**
	 * @return The current fight this actor is in.
	 */
	public Fight getFight() {
		return this.currentFight;
	}

	//-------------- Move methods -------------

	/**
	 * Tries to move the actor through an exit.
	 * if successful, the actor's location is modified
	 * to the exit's destination.
	 * @param the exit to go through
	 * @return if the actor succeeded passing through the exit.
	 */
	public boolean go(Exit target) {
		
		if (target.canCross()) {
			this.setLocation(target.getDestination());
			return true;
			
		} else {
			return false;
		}
		
	}
	
    @Override
    /**
     * See {@link Lookable}
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }
	
}
