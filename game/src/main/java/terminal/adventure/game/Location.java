package terminal.adventure.game;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Map<String, Exit> exits;

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
