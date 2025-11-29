package terminal.adventure.game.exits;
import terminal.adventure.game.Location;
import terminal.adventure.game.Player;

public abstract class Exit {
    protected Location destination;

    public Exit(Location destination) {
        this.destination = destination;
    }

    public Location getDestination() {
        return destination;
    }

    // Attempt to cross the exit
    public abstract boolean canCross(Player player);

    // Message to display if crossing fails
    public abstract String getFailMessage();
}
