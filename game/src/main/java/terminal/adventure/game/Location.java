package terminal.adventure.game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.exits.HiddenExit;
import terminal.adventure.game.interactables.Interactable;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.Storage;
import terminal.adventure.game.inventory.items.Item;

public class Location implements Lookable, Storage{
    private final String NAME;
    private final String DESCRIPTION;
	private List<Actor> actors;
    private final Map<String, Exit> exits;
	private List<Interactable> interactables;
	private Inventory inventory;

    public Location(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
		this.actors = new ArrayList<>();
		this.interactables = new ArrayList<>();
        this.exits = new HashMap<>();
		this.inventory = new Inventory();
    }

    /**
     * Appends an Exit with the an Identifier in the room.
     * @param nameOfDestination id of the Exit in the room, preferably the destination's name.
     * @param exit the Exit to add.
     */
    public void addExit(Exit exit) {
        exits.put(exit.getDestination().getName(), exit);
    }

    /**
     * Returns an Exit from its ID. If the ID is not linked to an Exit, returns null.
     * @param destinationName the Exit's ID
     * @return The associated Exit
     */
    public Exit getExit(String destinationName) {
        return exits.get(destinationName);
    }
	/**
	 * Getter of the Location's description
	 * @return The associated description
	 */
	public String getDescription(){
		return this.DESCRIPTION;
	}
    /**
     * Places an Actor in this location, and sets the actor's location to this one.
     * @param actor The actor to add
     */
	public void addActor(Actor actor){
		this.actors.add(actor);
		actor.setLocation(this);
	}

	
	/**
	 * Removes an Actor from the Location, and removes the Location from the Actor.
	 * @param actor The Actor to remove
	 */
	public void removeActor(Actor actor){
		actor.setLocation(null);
		this.actors.remove(actor);
	}

	/**
	 * Adds an Interactable Object in the Location.
	 * @param interactable The Interactable to add.
	 */
	public void addInteractable(Interactable interactable){
		this.interactables.add(interactable);
	}


	/**
	 * Looks for Actors with the given name.
	 * returns an Empty List if nothing found.
	 * @param name The name that is looked for.
	 * @return A list of found Actors.
	 */
	public List<Actor> getActorByName(String name){
		List<Actor> res = new ArrayList<>();
		for (Actor actor : this.actors) {
			if(actor.getName().equals(name)){
				res.add(actor);
			}
		}
		return res;
    }

	/**
	 * Looks for Exits with the given name.
	 * @param name The name that is looked for
	 * @return A list of found Exits
	 */
	public List<Exit> getExitByName(String name){
		List<Exit> res = new ArrayList<>();
		for (Exit exit : exits.values()) {
			if(exit.getName().equals(name)){
				res.add(exit);
			}
		}
		return res;
    }
	
	/**
	 * Looks for Exits that can be visible to the Actors.
	 * @return a map of the visible Exits // TODO : Change structure to List/other collection.
	 */
	public Map<String, Exit> getVisibleExits() {
        Map<String, Exit> visibleExits = new HashMap<>();
        for (Map.Entry<String, Exit> entry : exits.entrySet()) {
            if (!(entry.getValue() instanceof HiddenExit) || 
                !((HiddenExit) entry.getValue()).isHidden()) {
                visibleExits.put(entry.getKey(), entry.getValue());
            }
        }
        return visibleExits;
    }
	
	/**
	 * Gets a list of all Interactable Objects in this Location.
	 * @return a list of Interactables.
	 */
	public List<Interactable> getInteractables(){
		return this.interactables;
	}

	/**
	 * Location Name getter.
	 * @return The location's name.
	 */
    public String getName() { return this.NAME; }

    //------------------ Lookable Methods ------------
    
    
    @Override
    /**
     * Location Description getter.
     * @return The location's description
     */
    public String look() { return this.DESCRIPTION; }
	
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
