package terminal.adventure.game.inventory;

import java.util.List;

import terminal.adventure.game.inventory.items.Item;

public interface Storage {

    public abstract void addItem(Item item);
    
    public abstract void removeItem(Item item);
	
	public abstract List<Item> getItems();

}
