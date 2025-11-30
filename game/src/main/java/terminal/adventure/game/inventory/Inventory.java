package terminal.adventure.game.inventory;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.inventory.items.Item;

public class Inventory {

	List<Item> items;
	
	public Inventory() {
		this.items = new java.util.ArrayList<>();
	}
	
	public void add(Item item) {
		this.items.add(item);
	}
	
	public void remove(Item item) {
		this.items.remove(item);		
	}
	
	public List<Item> searchItemsByName(String name){
		List<Item> res = new ArrayList<Item>();
		
		for (Item i : items) {
			if (i.getName().equals(name)) {
				res.add(i);
			}
		}
		return res;
	}
}
