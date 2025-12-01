package terminal.adventure.game.controllers;

import terminal.adventure.exceptions.actorlessControllerException;

import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.Fight;

public abstract class Controller {

    protected Actor actor;

    private Fight currentFight = null;

    private Faction faction;
        
    public Controller(Actor actor, Faction faction) {
    	this.actor = actor;
    }
    
	protected abstract void play();
	
	public void playTurn() throws actorlessControllerException {
		if( this.actor == null) {
			throw new actorlessControllerException("playTurn called on actorless Controller.");
		}
		
		this.play();
	}
	
	
	
	
	public void equip(Item item) {
		try {
		this.actor.equipItem(item);
		} catch (terminal.adventure.exceptions.tooManyPossibilitesException e) {
			equipChooseSlot(item, e.getCandidates());
		}
	};
	
	public abstract void equipChooseSlot(Item item, List<Slot> candidates);
	
	public Actor getActor() {
		return this.actor;
	}
	
	public void enterFight(Fight f ) {
		this.leaveFight();
		this.currentFight = f;
	}
	
	public void leaveFight() {
		if (this.currentFight != null) {
			this.currentFight.removeFighter(this);
		}
	}
	
	public Fight getFight() {
		return this.currentFight;
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	public void die() {
		// playTurn will raise an Exception that needs to be catched in the turn queue.
		this.actor = null;
	}
}
