package terminal.adventure.game.controllers;

import java.util.HashMap;

import terminal.adventure.exceptions.tooManyPossibilitesException;

import java.util.List;
import java.util.Map;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.commands.Command;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

public abstract class Controller {

    protected Actor actor;
    private Faction faction;
    private final Map<String, Command> commands = new HashMap<>();
	
    public Controller(Actor actor, Faction faction) {
    	this.actor = actor;
    	this.faction = faction;
		registerCommands();
    }
    
    public Controller(Faction faction) {
    	this.faction = faction;
		registerCommands();
    }

	private void registerCommands() {
    	commands.put("GO", new CommandGo());
        commands.put("QUIT", new CommandQuit());
        commands.put("HELP", new CommandHelp());
        commands.put("LOOK", new CommandLook());
        commands.put("TAKE", new CommandTake());
        commands.put("USE", new CommandUse());
    }

    public Map<String, Command> getCommands() {
    	return this.commands;
    }
    
    
    // -------------- Inventory-related methods ----------------
    
    
	public boolean equip(Item item) {
		return this.equipOrder(item);
	};
	
	private boolean equipOrder(Item item) {
		return this.actor.equipItem(item, this);
	}
	
	public abstract int equipChooseSlot(List<String> candidatesNames); 
	

	// --------------- Faction Getter/Setter -------------------
	
	public Faction getFaction() {
		return this.faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	
	// --------------- Actor-related methods ------------------
	
	public Actor getActor() {
		return this.actor;
	}

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

	public void unbindActor() {
		if (this.actor != null) {
			Actor temp = this.actor;
			
			this.actor = null;
			temp.clearController();
		}
		
	}
	
	public void die() {
		// if Controller without an Actor, playTurn will raise an Exception that needs to be catched in the turn queue.
		this.unbindActor();
	}
	
	public boolean isDead() {
		return this.actor == null;
	}
	
	// ---------------- Comportement Methods ------------------
	
	protected abstract void play(); 
	
	public void playTurn() {
		if(!this.isDead()) {
			this.play();
		}	
	}
	
	
	
}