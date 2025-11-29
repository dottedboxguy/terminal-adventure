package terminal.adventure.game;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private String name;
    private String description;
    private Map<String, Location> exits;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void setExit(String direction, Location location) {
        exits.put(direction, location);
    }

    public Location getExit(String direction) {
        return exits.get(direction);
    }

    public String getDescription() {
        return description;
    }
}
