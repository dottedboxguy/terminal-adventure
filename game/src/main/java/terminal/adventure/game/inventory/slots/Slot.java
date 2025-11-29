package terminal.adventure.game.inventory.slots;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.items.Item;

public abstract class Slot {

	protected Item content = null;

	public abstract boolean canEquip(Item item);

	public boolean equip(Item item) {
		if (this.canEquip(item)){			
			this.content = item;
			return true;
		}
		return false;
	}

	public void clear() {
		this.content = null;
	}

	public Stats getStats() {
		if (this.content != null) {
			return this.content.getStats();
		}
		
		return null;
	}

	public boolean contains(Item item) {
		
		return this.content == item; // Checks if the actual item is contained, not just the same item

	}
	
}