package terminal.adventure.game.interactables;

import java.util.List;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;

public class Chest implements Storage, Interactable {

	private final String DESCRIPTION;
	private final String NAME;

    private Inventory inventory;
    private boolean isOpened = true;

	public Chest(String description, String name){
		this.DESCRIPTION = description;
		this.NAME = name;
		this.inventory = new Inventory();
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
	public String action(Actor actor){
    	
    	this.dump( actor.getCurrentLocation());        	
        return "You open the chest and scatter the items on the ground to look at them";
    };

	@Override
    /**
     * see {@link Interactable}
     */
	public String actionWithItem(Actor actor, Item item) {
		return "You don't see why you would do that";
	}
    
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

	@Override
  	/**
  	 * See {@link Storage}
  	 */
	public boolean contains(Item item) {
		for (Item i : this.getItems()) {
			if (i == item) {				
				return true;
			}
		}
		return false;
	}
  	
	@Override
    /**
     * see {@link Lookable}
     */
	public String look() {
		return this.DESCRIPTION;
	}

	public final String getName(){
		return this.NAME;
	}
  	

}