package terminal.adventure.game.interactables;

import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;

public class InteractableChest extends Interactable implements Storage {
    private Inventory inventory;
    private boolean isOpened = true;
	public InteractableChest(String description, String name, Inventory inventory){
		super(description, name);
	}
    
	
	/**
	 * Opens the chest
	 */
	public void open() {
		this.isOpened = true;
	}
	
	/**
	 * @return if the chest is open
	 */
	public boolean isOpen() {
		return isOpened;
	}
	
    @Override
    /**
     * see {@link Interactable}
     */
	public void action(Actor actor){
    	
    	this.dump( actor.getCurrentLocation());        	
        
    };
    
    //------------------- Storage Methods -------------
  	// For Documentation of these methods, see Storage
      
  	@Override
  	/**
  	 * See {@link Storage}
  	 */
  	public void addItem(Item item) {
  		this.inventory.add(item);
  		
  	}

  	@Override
  	/**
  	 * See {@link Storage}
  	 */
  	public void removeItem(Item item) {
  		this.inventory.add(item);
  	}

  	@Override
  	/**
  	 * See {@link Storage}
  	 */
  	public List<Item> getItems() {
  		return this.inventory.getItems();
  	}

  	@Override
  	/**
  	 * See {@link Storage}
  	 */
  	public List<Item> searchItems(String itemName) {
  		return this.inventory.searchItemsByName(itemName);
  	}

  	@Override
  	/**
  	 * See {@link Storage}
  	 */
  	public void dump(Storage target) {
  		for (Item item : this.getItems()){
  			target.addItem(item);
  		}
  		this.clear();
  	}		

  	@Override
  	/**
  	 * See {@link Storage}
  	 */
  	public void clear() {
  		this.inventory = new Inventory();
  	}
  	

}