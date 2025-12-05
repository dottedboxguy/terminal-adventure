package terminal.adventure.game.inventory;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.inventory.items.Item;

public class Inventory implements java.io.Serializable{

	private final List<Item> items;
	
	public Inventory() {
		this.items = new java.util.ArrayList<>();
	}
	
	/**
	 * Adds the specified item to the inventory
	 * @param item the item to add
	 */
	public void add(Item item) {
		this.items.add(item);
	}
	
	/**
	 * Removes if present the specified item from the inventory.
	 * @param item
	 */
	public void remove(Item item) {
		this.items.remove(item);		
	}
	
	/**
	 * Looks for items with specified name in the inventory.
	 * @param name the item name to look for.
	 * @return a list of items whose names match the searched one
	 */
	public List<Item> searchItemsByName(String name){
		List<Item> res = new ArrayList<>();
		
		for (Item i : items) {
			if (i.getName().equals(name)) {
				res.add(i);
			}
		}
		return res;
	}
	
	/**
	 * Takes an Item from a source storage to this one.
	 * Checks if the item is present in the source before attempting to take it.
	 * 
	 * @param source the source storage from which to take the item
	 * @param item the item to take from the source
	 * @return true if the item was successfully taken, false otherwise
	 * @throws NullPointerException if source or item is null
	 */
	public boolean takeItem(Storage source, Item item) {
		// Check for null parameters
		if (source == null || item == null) {
			throw new NullPointerException("Parameters cannot be null");
		}
		
		// Verify if the item is present in source beforehand 
		if (!source.contains(item)) {
			return false; // Item not in source
		}
		
		// Add to this storage first (if this fails, source remains unchanged)
		this.add(item);
		
		// Then remove from source
		source.removeItem(item);
		
		return true;
	}
	
	/**
	 * @return a list of items stored in this inventory.
	 */
	public List<Item> getItems(){ return this.items; }
}
