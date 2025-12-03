package terminal.adventure.game.exits;

import terminal.adventure.game.Location;

public class OpenExit extends Exit {

    public OpenExit(Location destination, String name, String description) {
        super(destination, name, description);
    }

    @Override
    public boolean canCross() {
        return true;
    }

    @Override
    public String getMessage() {
        return "You can cross freely.";
    }
}
