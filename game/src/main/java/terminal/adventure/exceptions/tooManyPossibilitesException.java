package exceptions;

import terminal.adventure.game.inventory.slots.Slot;
import java.util.List;

public class tooManyPossibilitesException extends terminalAdventureException {
	
	List<Slot> candidates;
	
	public tooManyPossibilitesException(String errorMessage, List<Slot> candidates) {
		super(errorMessage);
		this.candidates = candidates;
	}
	
	public List<Slot> getCandidates(){
		return candidates;
	}
	
}
