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
    public String unveil() {
        this.hidden = false;
        return "Woah, an exit has been unveiled !";
    }

    /**
     * @return whether this exit is still hidden.
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * see {@link Exit}
     * A hidden exit cannot be crossed until it is unveiled.
     */
    @Override
    public boolean canCross() {
        return !hidden;
    }

    /**
     * see {@link Exit}
     * Message displayed when the exit is hidden and crossing fails.
     */
    @Override
    public String getMessage() {
        return "You feel around, but find no visible way to go there.";
    }
}
