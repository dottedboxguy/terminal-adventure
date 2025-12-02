package terminal.adventure.game.inventory.slots;

import java.io.Serializable;

import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.items.Item;

public abstract class Slot implements Serializable {

	
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

	public boolean isEmpty() {
		return content == null;
	}
	
	public boolean contains(Item item) {
		
		return this.content == item; // Checks if the actual item is contained, not just one of the same item

	}
	
	public Item getItem() {
		return this.content;
	}
	
	@Override
	public String toString(){
		return "Slot"+this.getClass().getSimpleName()+"\n containing : "+this.content+"\n";
	}
	
}