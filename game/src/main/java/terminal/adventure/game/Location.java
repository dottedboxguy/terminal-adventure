package terminal.adventure.game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import terminal.adventure.game.characters.Character;
import terminal.adventure.game.exits.Exit;

public class Location implements Lookable{
    private final String NAME;
    private final String DESCRIPTION;
	private List<Character> characters;
    private final Map<String, Exit> exits;

    public Location(String name, String description) {
        this.NAME = name;
		this.characters = new ArrayList<>();
        this.DESCRIPTION = description;
        this.exits = new HashMap<>();
    }

    public void addExit(String nameOfDestination, Exit exit) {
        exits.put(nameOfDestination, exit);
    }

    public Exit getExit(String destinationName) {
        return exits.get(destinationName);
    }

	public void addCharacter(Character character){
		characters.add(character);
	}

	public List<Character> getCharacterByName(String name){
		List<Character> res = new ArrayList<>();
		for (Character character : this.characters) {
			if(character.getName().equals(name)){
				res.add(character);
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
