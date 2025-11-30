package terminal.adventure.game.inventory;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.exceptions.noPossibilitesException;
import terminal.adventure.exceptions.tooManyPossibilitesException;
import terminal.adventure.game.Stats;
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
	
	public List<Slot> findMatchingSlots(Item item){
		List<Slot> res = new ArrayList<>();
		
		for (Slot s : slots) {
			if (s.canEquip(item)) {
				res.add(s);
			}
		}
		
		return res;
	}
	
	public List<Slot> findSlotsbyType(Class<? extends Slot> type){
		List<Slot> res = new ArrayList<>();
		
		for (Slot s : slots) {
			if (type.isInstance(s)) {
				res.add(s);
			}
		}
		return res;
	}
	
	
	public void equip(Item item) throws noPossibilitesException, tooManyPossibilitesException{
		
		List<Slot> s = this.findMatchingSlots(item);
		if (s.isEmpty()) {
			throw new noPossibilitesException("no matching slot in equipment.");
		} else if (s.size() > 1) {
			throw new tooManyPossibilitesException("several possible slots found, add to one of the possible slots, see getMatchingSlots(Item item) function", s);
		}
		
		s.get(0).equip(item);	
	}
	
	
	public Stats totalStats() {
		Stats res = new Stats(); 
		for ( Slot s : slots) {
			if (  !s.isEmpty() ) {
				res.statsSum(s.getStats());
			}
		}
		return res;
	}

	
}
