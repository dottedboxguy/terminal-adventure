package terminal.adventure.game;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String name;
    private List<Stat> stats;

    public Item(String name) {
        this.name = name;
        this.stats = new ArrayList<>();
    }

    public void addStat(String name, int value) {
        stats.add(new Stat(name, value));
    }

    public int getStat(String name) {
    	
    	for ( Stat stat : this.stats) { 
    		if (stat.getName().equals(name)) {
    			return stat.getValue();
    		}
    	}
    	
    	return 0;
    }
    
    
    
    // Getters
    public String getName() { return name; }
    public List<Stat> getStats() { return stats; }
}
