package terminal.adventure.game.inventory.slots;
import terminal.adventure.game.inventory.items.GlovesEquipment;
import terminal.adventure.game.inventory.items.Item;


public class GlovesSlot extends Slot{

	@Override
	public boolean canEquip(Item item) {
		return item instanceof GlovesEquipment;
	}

}
