package terminal.adventure.game.spells;

import terminal.adventure.game.actors.Actor;

public abstract class Spell {
    private final String NAME;
    private final String DESCRIPTION;

    public Spell(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
    }

    public String getName() { return this.NAME; }
    public String getDescription() { return this.DESCRIPTION; }

    // Main effect of the spell
    public abstract void cast(Actor actor, Object target);
}
