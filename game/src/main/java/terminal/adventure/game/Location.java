package terminal.adventure.game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;

public class Location implements Lookable{
    private final String NAME;
    private final String DESCRIPTION;
	private List<Actor> actors;
    private final Map<String, Exit> exits;

    public Location(String name, String description) {
        this.NAME = name;
		this.actors = new ArrayList<>();
        this.DESCRIPTION = description;
        this.exits = new HashMap<>();
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

    public String getName() { return this.NAME; }

	@Override
    public String getDescription() { return this.DESCRIPTION; }
}
