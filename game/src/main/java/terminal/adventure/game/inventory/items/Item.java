package terminal.adventure.game.inventory.items;

import terminal.adventure.game.Lookable;
import terminal.adventure.game.Stats;

public class Item implements Lookable{
    private final String NAME;
    private final  String DESCRIPTION;
    private final Stats stats;
    
    public Item(String name, String description, Stats stats) {
        this.NAME = name;
        this.stats = stats;
        this.DESCRIPTION = description;
    }

    public Stats getStats() {
    	return this.stats;
    }

    public String getName() { return this.NAME; }

    @Override
    public String getDescription() {return this.DESCRIPTION;}
 
    @Override
    public String toString() {
    	return "Item : ("+this.getClass().getSimpleName()+") "+this.NAME+"\n";
    }
    
}
