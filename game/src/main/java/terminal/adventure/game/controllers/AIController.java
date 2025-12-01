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
	
	public AIController(Faction faction) {
		super(faction);
	}



	@Override
	public void equipChooseSlot(Item item, List<Slot> candidates) {
		candidates.get(0).equip(item);
	}



	@Override
	public void play() {
						
		Actor target = this.actor.getFight().getFightersByAntiFaction(this.getFaction()).get(0);
		
		this.actor.attack(target);
		
		
	}
}