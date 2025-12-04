package terminal.adventure.game.inventory.items;

import java.util.List;
import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.Storage;

public class Backpack extends Item implements Storage {

    public Inventory inventory;
    
    public Backpack(String name, String description){
        super(name, description, new Stats());
        this.inventory = new Inventory();
    }

	@Override
	/**
	 * see {@link Storage}
	 */
	public void addItem(Item item) {
		this.inventory.add(item);
	}

	@Override
	/**
	 * see {@link Storage}
	 */
	public void removeItem(Item item) {
		this.inventory.remove(item);
	}

	@Override
	/**
	 * see {@link Storage}
	 */
	public List<Item> getItems() {
		this.inventory.getItems();
		return null;
	}

	@Override
	/**
	 * see {@link Storage}
	 */
	public List<Item> searchItems(String itemName) {
		return inventory.searchItemsByName(itemName);
	}

	@Override
	public void dump(Storage target) {
		for (Item item : this.getItems()){
			target.addItem(item);
		}
		this.clear();
	}		

	@Override
	public void clear() {
		this.inventory = new Inventory();
	}
	
}
