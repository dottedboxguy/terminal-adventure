package terminal.adventure.game.inventory;

import java.util.List;

import terminal.adventure.game.inventory.items.Item;

public interface Storage {

	/**
	 * Adds an item to the Storage
	 * @param item the item to add
	 */
    public abstract void addItem(Item item);


	/**
	 * Removes an Item to the Storage.
	 * Does nothing if the item is not in the storage
	 * @param item
	 */
    public abstract void removeItem(Item item);
	
    /**
     * @return a list of all the stored items.
     */
	public abstract List<Item> getItems();

	/**
	 * looks for items with the given name.
	 * @param itemName the item name looked for
	 * @return a list of items with a matching name.
	 */
	public abstract List<Item> searchItems(String itemName);

	
	/**
	 * Dumps all of the stored items into the target Storage.
	 * @param target the Storage where you dump the item
	 */
	public abstract void dump(Storage target);
	
	public abstract void clear();
	
}
