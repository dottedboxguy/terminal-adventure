package terminal.adventure.game.inventory.items;

import terminal.adventure.game.Stats;

public class Item {
    private String name;
    private String description;
    private final Stats stats;

    public Item(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.description = "<default-description>";
    }

    public Stats getStats() {
    	return this.stats;
    }
    
    public String getName() { return name; }
    
    public String getDescription() {return description;}
    
}
