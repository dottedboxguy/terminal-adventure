package terminal.adventure.game.inventory;

import java.util.List;

import terminal.adventure.game.inventory.items.Item;

public class Inventory {

	List<Item> items;
	
	public Inventory() {
		this.items = new java.util.ArrayList<Item>();
	}
	
	public void add(Item item) {
		this.items.add(item);
	}
	
	public void remove(Item item) {
		this.items.remove(item);		
	}
	
}
