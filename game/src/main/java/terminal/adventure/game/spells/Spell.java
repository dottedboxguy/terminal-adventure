package terminal.adventure.game.spells;

import java.util.List;

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
     * @return The return message
     */
    public abstract String cast(String[] args, Actor caster);
    
    /**
     * Looks for a suitable target with the specified name, accessible for the caster. 
     * @param args the target arguments.
     * @param caster The actor that casts the spell
     * @return a list target Objects.
     */
    public abstract List<Object> getTargets(String[] args, Actor caster);

}
