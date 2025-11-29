package terminal.adventure.game;

public abstract class Spell {
    private String name;
    private String description;

    public Spell(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    // Main effect of the spell
    public abstract void cast(GameState game, Player player, Object target);
}
