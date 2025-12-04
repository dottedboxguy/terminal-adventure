package terminal.adventure.game.inventory;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.Stats;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.inventory.items.Item;
import terminal.adventure.game.inventory.slots.Slot;

public class Equipment {

	List<Slot> slots;
	
	public Equipment(List<? extends Slot> typedSlots) {
		this.slots = new ArrayList<>();
		
		for (Slot s : typedSlots) {
			slots.add(s);
		}

	}
	/**
	 * Tries to equip the specified item.
	 * @param item the item to equip.
	 * @param controller the controller to ask if several slots possibilities.
	 * @return if the item has been successfully equipped.
	 */
	public boolean equipItem(Item item, Controller controller) {
	
		List<Slot> matching = this.findMatchingSlots(item);
		
		int index = -1;
		
		if (matching.size() == 0) {
			return false;
		} else if (matching.size() == 1 )
			index = 0;
		else {
		
			List<String> choicesNames  = new ArrayList<>();
			
			for (Slot s : matching) {
				if (s.isEmpty()) {
					choicesNames.add(null);
				} else {
					choicesNames.add(s.getItem().getName());
				}
			}
			
			index = controller.equipChooseSlot(choicesNames);
		
		}
		
		return matching.get(index).equip(item); 
	}
	
	/**
	 * finds Slots in the equippement that can hold the given item.
	 * @param item the item to look slots for.
	 * @return a list of compatible slots.
	 */
	private List<Slot> findMatchingSlots(Item item){
		List<Slot> slotRes = new ArrayList<>();		
		
		for (Slot s : slots) {
			if (s.canEquip(item)) {
				slotRes.add(s);
			}
		}
		
		return slotRes;
	}
	
	/**
	 * @return if at least one Storage item is equipped.
	 */
	public boolean containsStorage() {
		return this.getfirstStorage() != null;
	}
	
	/**
	 * @return the first found Storage item equipped.
	 */
	public Storage getfirstStorage() {
		for (Slot s : this.slots) {
			if (s.getItem() instanceof Storage) {
				return (Storage) s.getItem();
			}
		}
		return null;
	}
	
	/**
	 * @return a list of all Storage items equipped.
	 */
	public List<Storage> getAllStorages() {
		List<Storage> storages = new ArrayList<>();
		
		for (Slot s : this.slots) {
			if (s.getItem() instanceof Storage) {
				storages.add((Storage) s.getItem());
			}
		}
		return storages;
	}
	
	/**
	 * Adds up all of the equipped item's stats
	 * @return the sum of all items's slots.
	 */
	public Stats totalStats() {
		Stats res = new Stats(); 
		for ( Slot s : slots) {
			if (  !s.isEmpty() ) {
				res = res.statsSum(s.getStats());
			}
		}
		return res;
	}

}
