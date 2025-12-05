package terminal.adventure.game.controllers;

import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;

public abstract class Controller {

	/**
	 * The controlled actor
	 */
    protected Actor actor;
    
    /**
     * The controller's faction
     */
    private Faction faction;

    public Controller(Actor actor, Faction faction) {
    	this.actor = actor;
    	this.faction = faction;
    }
    
    public Controller(Faction faction) {
    	this.faction = faction;
    }
    
    // -------------- Inventory-related methods ----------------
    
    /**
     * Gives the instruction to the actor to equip the given item.
     * @param item the item to equip.
     * @return if the item has successfully been equipped.
     */
	public boolean equip(Item item) {
		return this.actor.equip(item, this);
	};

	/**
	 * Called if several item slots are available during an equipment process,
	 * Chooses which slot is selected.
	 * @param candidatesNames The names of the candidates slots's contents, null if empty.
	 * @return The index of the selected item in the candidates Name list given.
	 */
	public abstract int equipChooseSlot(List<String> candidatesNames); 
	

	// --------------- Faction Getter/Setter -------------------

	/**
	 * @return this Controllers current faction
	 */
	public Faction getFaction() {
		return this.faction;
	}

	/**
	 * @param faction the new faction of the controller
	 */
	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	
	// --------------- Actor-related methods ------------------
	
	/**
	 * @return the controlled actor.
	 */
	public Actor getActor() {
		return this.actor;
	}

	/**
	 * @param the new controlled actor.
	 * If there already was a controlled actor,
	 * it will be unbound.
	 */
	public void bindActor(Actor a) {

		// Disconnecting old actor from this Controller		
		this.unbindActor();
		
		
		// Setting the new actor as this Controller's actor
		this.actor = a;
		
		// If the new actor's Controller isn't already this one, set it.
		if (a.getController() != this) {
			a.setController(this);			
		}
		
		
	}

	/**
	 * Unbinds the current controlled actor.
	 */
	public void unbindActor() {
		if (this.actor != null) {
			Actor temp = this.actor;
			
			this.actor = null;
			temp.clearController();
		}
		
	}
	
	/**
	 * Process called if the controlled actor dies.
	 * Unbinds the controlled actor.
	 */
	public void die() {
		this.unbindActor();
	}
	
	/**
	 * Returns if the controller is Empty
	 * That can mean the once controlled actor is dead.
	 * @return
	 */
	public boolean isDead() {
		return this.actor == null;
	}
	
	// ---------------- Comportement Methods ------------------
	
	/**
	 * The abstract method called at each turn.
	 * The method actually describe the whole
	 * controlled actor behaviour.
	 */
	protected abstract void play(); 
	
	/**
	 * Wrapper called by the GameState to start turn.
	 * used to condition the capability for the  to play.
	 */
	public void playTurn() {
		if(!this.isDead()) {
			this.play();
		}	
	}
	
}