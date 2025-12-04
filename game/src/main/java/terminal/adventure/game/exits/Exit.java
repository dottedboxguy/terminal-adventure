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

    /**
     * @return if the exit can be crossed
     */
    public abstract boolean canCross();

   /**
    * @return the message to return if crossing fails.
    */
    public abstract String getMessage();

    /**
     * @return this exit's destination.
     */
    public Location getDestination(){return destination;}
    
    /**
     * @return this exit's name.
     */
    public String getName(){return this.NAME;}

    @Override
    /**
     * 	see {@link Lookable}
     */
    public String look(){return this.DESCRIPTION;}
    
}
