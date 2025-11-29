package terminal.adventure.game.exits;

import terminal.adventure.game.Location;
import terminal.adventure.game.Player;

public class HiddenExit extends Exit {

    private boolean hidden;

    public HiddenExit(Location destination) {
        super(destination);
        this.hidden = true;
    }

    /**
     * Reveal the exit to the player.
     */
    public void unveil() {
        this.hidden = false;
    }

    /**
     * Returns whether this exit is still hidden.
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * A hidden exit cannot be crossed until it is unveiled.
     */
    @Override
    public boolean canCross(Player player) {
        return !hidden;
    }

    /**
     * Message displayed when the exit is hidden and crossing fails.
     */
    @Override
    public String getFailMessage() {
        if (hidden) {
            return "You feel around, but find no visible way to go there.";
        }
        return "You cannot cross this exit.";
    }
}
