package terminal.adventure.game;

import java.util.*;

public class Location {

	private Collection<Character> characters;
	private Collection<Exit> exits;
	private Collection<Interactable> Interactable;
	private Collection<Item> availableItems;
	private String name;
	private String description;

	/**
	 * 
	 * @param name
	 * @param description
	 */
	public Location(String name, String description) {
		// TODO - implement Location.Location
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public List<Exit> getExits() {
		// TODO - implement Location.getExits
		throw new UnsupportedOperationException();
	}

}