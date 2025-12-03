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
	
	
	public List<Slot> findMatchingSlots(Item item){
		List<Slot> slotRes = new ArrayList<>();		
		
		for (Slot s : slots) {
			if (s.canEquip(item)) {
				slotRes.add(s);
			}
		}
		
		return slotRes;
	}
	
	public boolean containsBackpack() {
		return this.getfirstStorage() != null;
	}
	
	public Storage getfirstStorage() {
		for (Slot s : this.slots) {
			if (s.getItem() instanceof Storage) {
				return (Storage) s.getItem();
			}
		}
		return null;
	}
	
	
	
	private List<Slot> findSlotsbyType(Class<? extends Slot> type){
		List<Slot> res = new ArrayList<>();
		
		for (Slot s : slots) {
			if (type.isInstance(s)) {
				res.add(s);
			}
		}
		return res;
	}
	
	
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
