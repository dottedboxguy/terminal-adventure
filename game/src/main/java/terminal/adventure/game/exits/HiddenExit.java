package terminal.adventure.game.exits;

import terminal.adventure.game.Location;

public class HiddenExit extends Exit {

    private boolean hidden;

    public HiddenExit(Location destination, String name, String description) {
        super(destination, name, description);
        this.hidden = true;
    }

    /**
     * Reveal the exit to the character.
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
    public boolean canCross() {
        return !hidden;
    }

    /**
     * Message displayed when the exit is hidden and crossing fails.
     */
    @Override
    public String getMessage() {
        if (hidden) {
            return "You feel around, but find no visible way to go there.";
        }
        return "You cannot cross this exit.";
    }
}
