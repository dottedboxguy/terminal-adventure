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

    /**
     * @return this item's stats
     */
    public Stats getStats() {
    	return this.stats;
    }
    @Override
    /**
     * @return the item's name
     */
    public String getName() { return this.NAME; }

    @Override
	/**
	 * see {@link Lookable}
	 */
    public String look() {return this.DESCRIPTION;}
 
    @Override
    /**
     * @return a String representing the Item
     */
    public String toString() {
    	return "Item : ("+this.getClass().getSimpleName()+") "+this.NAME+"\n";
    }
    
}
