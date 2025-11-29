package terminal.adventure.game.inventory.slots;
import terminal.adventure.game.inventory.items.BootsEquipment;
import terminal.adventure.game.inventory.items.Item;


public class BootsSlot extends Slot{

	@Override
	public boolean canEquip(Item item) {
		return item instanceof BootsEquipment;
	}

}
