package terminal.adventure.game;

import java.util.Collection;
import java.util.Map;

public abstract class Item {
	public final String NAME;
	Collection<Spell> spells;
	private Map<String,Integer> stats;
	

	public Item(String name) {
		this.NAME = name;
		this.spells = null;
		this.stats = null;
	}
	public Item() {
		this.NAME = null;
		this.spells = null;
		this.stats = null;
	}

}