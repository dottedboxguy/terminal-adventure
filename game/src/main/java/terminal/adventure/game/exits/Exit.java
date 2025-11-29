package terminal.adventure.game.exits;
import terminal.adventure.game.Location;

public abstract class Exit {
    protected Location destination;
    protected final String NAME;

    public Exit(Location destination, String name) {
        this.destination = destination;
        this.NAME = name;
    }

    public Location getDestination() {
        return destination;
    }

    // Attempt to cross the exit
    public abstract boolean canCross(Character player);

    // Message to display if crossing fails
    public abstract String getFailMessage();

    public String getName(){ return this.NAME;}
}
