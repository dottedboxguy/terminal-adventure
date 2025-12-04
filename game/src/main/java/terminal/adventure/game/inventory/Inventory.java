package terminal.adventure.game.inventory;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.inventory.items.Item;

public class Inventory {

	private List<Item> items;
	
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
	 * takes an Item from a source storage to this one.
	 * @param source the source inventory.
	 * @param item the item to take.
	 * TODO:verify if the item is present in source beforehand.
	 */
	public void takeItem(Storage source, Item item){
		this.add(item);
		source.removeItem(item);
	}
	/**
	 * Takes all items from a Storage source to this inventory.
	 * TODO:call takeItem instead of duplicating code
	 */
	public void takeAll(Storage source){
		for (Item item : source.getItems()){
			this.add(item);
			source.removeItem(item);
		}
	}
	/**
	 * @return a list of items stored in this inventory.
	 */
	public List<Item> getItems(){ return this.items; }
}
