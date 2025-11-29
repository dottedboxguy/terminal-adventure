package terminal.adventure.game.inventory.slots;
import terminal.adventure.game.inventory.items.HeadEquipment;
import terminal.adventure.game.inventory.items.Item;


public class HeadSlot extends Slot{

	@Override
	public boolean canEquip(Item item) {
		return item instanceof HeadEquipment;
	}

}
