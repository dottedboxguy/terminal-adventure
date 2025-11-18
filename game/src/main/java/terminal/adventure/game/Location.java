package terminal.adventure.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Location {

	private List<Character> characters;
	private List<Interactable> Interactables;
	private List<Item> availableItems;
	private Map<String,Exit> exits;
	private String name;

	/**
	 * 
	 * @param name : Name of the location
	 * @param description : Description of the location
	 */
	public Location(String name) {
		this.name = name;
		this.characters = new ArrayList<Character>();
		this.exits = new HashMap<String,Exit>();
		this.availableItems = new ArrayList<Item>();
		this.Interactables = new ArrayList<Interactable>();
	}

	public String getName() {
		return this.name;
	}

	public Exit getExitFromKey(String key) {
		return this.exits.get(key);
	}

}