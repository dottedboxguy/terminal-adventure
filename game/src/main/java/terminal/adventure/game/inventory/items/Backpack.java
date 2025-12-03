package terminal.adventure.game.inventory.items;

import java.util.List;
import terminal.adventure.game.Stats;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.Storage;

public class Backpack extends Item implements Storage {

    public Inventory inventory;
    
    public Backpack(String name){
        super(name, new Stats());
        this.inventory = new Inventory();
    }

	@Override
	public void addItem(Item item) {
		this.inventory.add(item);
	}

	@Override
	public void removeItem(Item item) {
		this.inventory.remove(item);
	}

	@Override
	public List<Item> getItems() {
		this.inventory.getItems();
		return null;
	}

	@Override
	public List<Item> searchItems(String itemName) {
		return inventory.searchItemsByName(itemName);
	}
}
