package terminal.adventure.game.spells;

import terminal.adventure.game.actors.Actor;

public abstract class Spell {
    private final String NAME;
    private final String DESCRIPTION;

    public Spell(String name, String description) {
        this.NAME = name;
        this.DESCRIPTION = description;
    }

    /**
     * @return the name of this spell.
     */
    public String getName() { return this.NAME; }
    
    /**
     * @return this spell's description.
     */
    public String getDescription() { return this.DESCRIPTION; }

    /**
     * The spell's effect on the environnement.
     * @param actor the caster actor
     * @param target the upcasted target on which the spell is casted.
     */
    public abstract void cast(Actor actor, Object target);
}
