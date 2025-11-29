package terminal.adventure.game;
import java.util.HashMap;
import java.util.Map;

import terminal.adventure.game.exits.Exit;

public class Location {
    private final String name;
    private final String description;
    private final Map<String, Exit> exits;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void addExit(String nameOfDestination, Exit exit) {
        exits.put(nameOfDestination, exit);
    }

    public Exit getExit(String destinationName) {
        return exits.get(destinationName);
    }

    public String getName() { return name; }

    public String getDescription() { return description; }
}
