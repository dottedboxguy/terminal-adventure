package terminal.adventure.game.exits;
import terminal.adventure.game.Location;
import terminal.adventure.game.Lookable;

public abstract class Exit implements Lookable{
    protected Location destination;
    protected final String NAME;
    private final String DESCRIPTION;

    public Exit(Location destination, String name, String description) {
        this.destination = destination;
        this.NAME = name;
        this.DESCRIPTION = description;
    }

    public Location getDestination() {
        return destination;
    }

    // Attempt to cross the exit
    public abstract boolean canCross();

    // Message to display if crossing fails
    public abstract String getMessage();

    @Override
    public String getDescription(){
        return this.DESCRIPTION;
    }

    public String getName(){ return this.NAME;}
}
