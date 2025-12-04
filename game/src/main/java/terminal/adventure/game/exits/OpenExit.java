package terminal.adventure.game.exits;

import terminal.adventure.game.Location;

public class OpenExit extends Exit {

    public OpenExit(Location destination, String name, String description) {
        super(destination, name, description);
    }

    @Override
    /**
     * see {@link Exit}
     */
    public boolean canCross() {
        return true;
    }

    @Override
    /**
     * see {@link Exit}
     */
    public String getMessage() {
        return "You can cross freely.";
    }
}
