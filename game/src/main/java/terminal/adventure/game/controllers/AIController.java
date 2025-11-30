package terminal.adventure.game.controllers;

import java.util.List;

import terminal.adventure.game.Fight;
import terminal.adventure.game.characters.Character;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

public class AIController extends Controller {

	public AIController(Character character) {
		super(character);
	}

	@Override
	public void FightTurn(Fight fight) {
		
		Controller targetController = fight.getEnnemies().get(0);
		Character target = targetController.getCharacter();
		
		attack(target);
		if (target.isDead()) {
			fight.removeEnnemy(targetController);
		}
		
		System.out.println(this.character.NAME + " attacks "+ target.NAME);
		
		
	}

	@Override
	public void equipChooseSlot(Item item, List<Slot> candidates) {
		candidates.get(0).equip(item);
	}

}