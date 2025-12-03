package terminal.adventure.game.inventory.slots;
import terminal.adventure.game.inventory.items.Backpack;
import terminal.adventure.game.inventory.items.Item;


public class BackpackSlot extends Slot{

	@Override
	public boolean canEquip(Item item) {
		return item instanceof Backpack;
	}

}
