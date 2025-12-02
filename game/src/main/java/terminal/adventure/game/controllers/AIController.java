package terminal.adventure.game.controllers;

import java.util.List;
import java.util.Random;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

public class AIController extends Controller {

	Random random = new Random();
	
	public AIController(Actor actor, Faction faction) {
		super(actor, faction);
	}
	
	public AIController(Faction faction) {
		super(faction);
	}



	@Override
	public void equipChooseSlot(Item item, List<Slot> candidates) {
		candidates.get(0).equip(item);
	}



	@Override
	public void play() {
						
		if (this.actor.getFight() != null) {

			Actor target = this.actor.getFight().getFightersByAntiFaction(this.getFaction()).get(0);
			
			this.actor.attack(target);
		} else {
		
			List<Exit> exits = this.actor.getCurrentLocation().getAllExits();
			
			
			int nExits = exits.size();
			if ( nExits > 0) {
				
				int e = random.nextInt( nExits );
				
				//this.actor.move(exits[e});
				
			}
			
			
			
			
		}
	}
}