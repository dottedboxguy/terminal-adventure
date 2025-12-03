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
		actor.setLocation(this);
	}

	public void removeActor(Actor actor){
		actor.setLocation(null);
		this.actors.remove(actor);
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
	public List<Interactable> getInteractables(){
		return this.interactables;
	}

	public Inventory getInventory() { return this.inventory; }

    public String getName() { return this.NAME; }

    public String getDescription() { return this.DESCRIPTION; }
}
