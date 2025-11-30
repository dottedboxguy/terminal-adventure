package terminal.adventure.game.controllers;

import java.util.List;

import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;
import terminal.adventure.game.Fight;
import terminal.adventure.game.characters.Character;

public abstract class Controller {

    protected Character character;

    public Controller(Character character) {
    	this.character = character;
    }
    
    
	public abstract void FightTurn(Fight fight);

	public void equip(Item item) {
		try {
		this.character.equipItem(item);
		} catch (exceptions.tooManyPossibilitesException e) {
			equipChooseSlot(item, e.getCandidates());
		}
	};
	
	public abstract void equipChooseSlot(Item item, List<Slot> candidates);
	
	public void attack(Character target) {
		
		
		target.takeAttack( character.getBaseStats().getStrength() );
		
		
	}

	public Character getCharacter() {
		return this.character;
	}
	
}
