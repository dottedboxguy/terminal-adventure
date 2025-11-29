package terminal.adventure.game.exits;

import terminal.adventure.game.Location;
import terminal.adventure.game.Player;

public class OpenExit extends Exit {

    public OpenExit(Location destination) {
        super(destination);
    }

    @Override
    public boolean canCross(Player player) {
        return true;
    }

    @Override
    public String getFailMessage() {
        return "You can cross freely.";
    }
}
