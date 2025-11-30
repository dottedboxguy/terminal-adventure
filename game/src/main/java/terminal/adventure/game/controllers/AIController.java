package terminal.adventure.game.controllers;

import java.util.List;

import terminal.adventure.game.characters.Character;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

public class AIController extends Controller {

	public AIController(Character character) {
		super(character);
	}

	@Override
	public void FightTurn(Object combat) {
		attack(combat.getOpponents().get(0));
		
	}

	@Override
	public void equipChooseSlot(Item item, List<Slot> candidates) {
		candidates.get(0).equip(item);
	}

}