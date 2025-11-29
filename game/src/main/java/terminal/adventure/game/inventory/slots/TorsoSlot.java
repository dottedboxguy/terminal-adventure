package terminal.adventure.game.inventory.slots;
import terminal.adventure.game.inventory.items.TorsoEquipment;
import terminal.adventure.game.inventory.items.Item;

public class TorsoSlot extends Slot{

	@Override
	public boolean canEquip(Item item) {
		return item instanceof TorsoEquipment;
	}

}
