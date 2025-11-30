package terminal.adventure.game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.interactables.Interactable;
import terminal.adventure.game.inventory.Inventory;
import terminal.adventure.game.inventory.items.Item;

public class Location implements Lookable{
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

    public void addExit(String nameOfDestination, Exit exit) {
        exits.put(nameOfDestination, exit);
    }

    public Exit getExit(String destinationName) {
        return exits.get(destinationName);
    }

	public void addActor(Actor actor){
		this.actors.add(actor);
	}

	public void addInteractable(Interactable interactable){
		this.interactables.add(interactable);
	}

	public void addToInventory(Item item){
		inventory.add(item);
	}

	public List<Actor> getActorByName(String name){
		List<Actor> res = new ArrayList<>();
		for (Actor actor : this.actors) {
			if(actor.getName().equals(name)){
				res.add(actor);
			}
		}
		return res;
    }

	public List<Exit> getExitByName(String name){
		List<Exit> res = new ArrayList<>();
		for (Exit exit : exits.values()) {
			if(exit.getName().equals(name)){
				res.add(exit);
			}
		}
		return res;
    }

	public List<Interactable> getInteractables(){
		return this.interactables;
	}

    public String getName() { return this.NAME; }
	public Inventory getInventory() { return this.inventory; }
	@Override
    public String getDescription() { return ""; }
}
