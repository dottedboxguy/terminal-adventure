package terminal.adventure.game.exits;

import terminal.adventure.game.Location;

public class OpenExit extends Exit {

    public OpenExit(Location destination, String name) {
        super(destination, name);
    }

    @Override
    public boolean canCross(Character player) {
        return true;
    }

    @Override
    public String getFailMessage() {
        return "You can cross freely.";
    }
}
