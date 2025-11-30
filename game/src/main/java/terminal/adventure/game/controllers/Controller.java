package terminal.adventure.game.controllers;

import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.Fight;

public abstract class Controller {

    protected Actor actor;

    public Controller(Actor actor) {
    	this.actor = actor;
    }
    
    
	public abstract void FightTurn(Fight fight);

	public void equip(Item item) {
		try {
		this.actor.equipItem(item);
		} catch (exceptions.tooManyPossibilitesException e) {
			equipChooseSlot(item, e.getCandidates());
		}
	};
	
	public abstract void equipChooseSlot(Item item, List<Slot> candidates);
	
	public void attack(Actor target) {
		
		target.takeAttack( actor.getBaseStats().getStrength() );

		
	}

	public Actor getActor() {
		return this.actor;
	}
	
}
