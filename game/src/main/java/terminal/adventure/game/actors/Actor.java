package terminal.adventure.game.actors;

import java.util.ArrayList;
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
import terminal.adventure.game.inventory.slots.BackpackSlot;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.spells.Spell;


public abstract class Actor implements Lookable{
    
    public final String NAME;
    public final String DESCRIPTION;
    protected Equipment equipment;
    protected Stats baseStats;
    private Location currentLocation;
    
    private Controller controller;
    private Fight currentFight = null;
    
    private List<Spell> knownSpells;
    
    
    public Actor(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
        this.baseStats = new Stats();
		List<Slot> slots = makeSlots();
		slots.add(new BackpackSlot());
		this.equipment = new Equipment(slots);
		
		this.knownSpells = new ArrayList<Spell>();
	}

	public abstract List<Slot> makeSlots();
    
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

    /**
     * Equips if possible the specified item.
     * @param item the item to equip
     * @param controller the controller to refer to if several possibilites
     * @return if success
     */
    public boolean equip(Item item, Controller controller) {
    	return this.equipment.equipItem(item, controller);
    }
    
    /**
     * Equips if possible the specified item if it's contained in the
     * source storage, and removes it from this source.
     * @param item the item to equip
     * @param controller the controller to refer to if several possibilites
     * @param source the Storage the item is from.
     * @return if success
     */
    public boolean equipFrom(Item item, Controller controller, Storage source) {
    
    	if ( source.contains(item)){
    		if (this.equipment.equipItem(item, controller)){
	    		source.removeItem(item);
	    		return true;
	    	}
    		return false;
    	}
    	return false;
    	
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
     * @return a Stats Object, containg the remaining health, and the real damage amount as the Strength stat,
     */
    public Stats takeAttack(int attackPower) {
    	
    	int currentHealth = this.getBaseStats().getCurrentHealth();
    	
    	int damage = (attackPower - this.getTotalStats().getArmor());
    	
    	this.getBaseStats().setCurrentHealth(currentHealth - damage);
    	
    	if (this.isDead()) {
    		System.out.println("DEBUG Actor.takeAttack : OOOFFFF Im "+ this.NAME+ " and I dramatically died.");
    		this.die();
    	} else {
    		
    		System.out.println("DEBUG Actor.takeAttack : Ouch ! I'm "+this.NAME+" and I have "+this.getBaseStats().getCurrentHealth()+" HP remaing");
    	}
    
    	Stats ret = new Stats();
    	ret.setStrength(damage);
    	ret.setCurrentHealth(this.getBaseStats().getCurrentHealth());
    	
    	return ret;

    }
    
    /**
     * Called whenever the actor should die.
     * Tells the controller its actor is dead, which disconnects it.
     */
    public void die() {
    	// Actions to perform at death (loot drop, events, etc)
    	
    	this.leaveFight();
    
    	this.getFirstStorage().dump(this.currentLocation);

    	this.getCurrentLocation().removeActor(this);
    	
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
     * If either one of the attacker or defender is currently in a fight,
     * the other one joins in.
     * If neither of those are in a fight, they both join a new fight.
     * If both of those are in a fight, the attacker joins the defenser's.
     * 
     * @param target the actor to attack.
     * @return A Stats Object containing the Remaining health, and the effective damage dealt as Strenght stat.
     */
    public Stats attack(Actor target) {
    	
    	System.out.println("DEBUG attack:"+ this.getFight() + target.getFight() );
    	    	
    	if (this.getFight() != target.getFight() || target.getFight() == null) {
    		
    		
			if ( this.getFight() == null ) {
			
				
				if (target.getFight() == null) { // if none of the attacker and defender are in a fight
					Fight f = new Fight();
					this.enterFight(f);
					target.enterFight(f);
				} else { // if the defender is in a fight and not the attacker
					
					this.enterFight( target.getFight() );
					
				}
	
			} else {
				
				if (target.getFight() == null) { // if the attacker is in a fight and not the defender
					target.enterFight(this.getFight());
					
				} else {
					
					this.enterFight(target.getFight()); // if both are in a different fight
					
				}
					
			}
    	
    	}

    	
    	return target.takeAttack(this.getBaseStats().getStrength());

    	
    }

    /**
     * Puts the given item in the first Storage found in the actor's Inventory.
     * @param item the item to add to the inventory.
	 * @param source the source storage from which the item originates
     * @return if the addition was successful. (can fail if no Storage is equipped).
     */
    public boolean takeItem(Item item, Storage source) {
    	Storage inventory = this.getFirstStorage();
    	if (inventory != null) {
    		inventory.addItem(item);
			source.removeItem(item);
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
    
    public List<Storage> getAllStorages(){
    	return this.equipment.getAllStorages();
    }
    
    @Override
    public String look() {
        return "-" + this.NAME + ":\n" + this.DESCRIPTION;
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
	
	/**
	 * Adds a spell to the list of known spells.
	 * does nothing if spell already known.
	 * @param spell
	 */
	public void learnSpell(Spell spell) {
		if (!this.knownSpells.contains(spell)){
			this.knownSpells.add(spell);
		}
	}
	
	/**
	 * Removes a spell from the list of known spells.
	 * does nothing if spell not known
	 * @param spell
	 */
	public void forgetSpell(Spell spell) {
		if (this.knownSpells.contains(spell)){
			this.knownSpells.remove(spell);
		}
	}

	/**
	 * @return the list of known spells.
	 */
	public List<Spell> getSpells() {
		return this.knownSpells;
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
		
		if (target.canCross() && this.getFight() == null) {
			this.setLocation(target.getDestination());
			return true;
			
		} else {
			return false;
		}
		
	}
	
}
