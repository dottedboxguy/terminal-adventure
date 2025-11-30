package terminal.adventure.game.controllers;

import java.util.List;

import terminal.adventure.game.Fight;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

public class AIController extends Controller {

	public AIController(Actor actor, Faction faction) {
		super(actor, faction);
	}



	@Override
	public void equipChooseSlot(Item item, List<Slot> candidates) {
		candidates.get(0).equip(item);
	}



	@Override
	public void play() {
		Fight f = this.getFight();
		if ( f == null) {
			// out of fight action
		} else {
		
			if (this.getFaction() == Faction.badGuys) {				
				List<Actor> ennemies = f.getFightersByFaction(Faction.goodGuys);
				
				if (ennemies.size() == 0) {
					this.leaveFight();
				} else {
					this.getActor().attack(ennemies.get(0));
				}
				
				
			}
			
			
			
			
		}
		
		
	}

}