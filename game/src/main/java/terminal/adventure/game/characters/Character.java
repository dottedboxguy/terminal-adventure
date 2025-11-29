package terminal.adventure.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Character {
    
    private String name;
    private List<Stat> baseStats;
    private List<Item> equippedItems;
    private List<Slot> inventory;
    
    
    
    public Character(String name) {
        this.name = name;
        this.baseStats = new ArrayList<>();
        this.inventory = new ArrayList<>();
        
        
    }

    public void addBaseStat(String name, int value) {
        baseStats.add(new Stat(name, value));
    }

    public void equipItem(Item item) {
        equippedItems.add(item);
    }

    public int getTotalStatValue(String statName) {
        int total = 0;

        for (Stat stat : baseStats) {
            if (stat.getName().equals(statName)) {
                total += stat.getValue();
            }
        }

        
        for (Item item : equippedItems) {
            for (Stat stat : item.getStats()) {
                if (stat.getName().equals(statName)) {
                    total += stat.getValue();
                }
            }
        }
        return total;
    }

    // Getters
    public String getName() { return name; }
    
    public int getTotalStat(String name) {
    	int res = 0;
    	
    	// Base Stats
    	for ( Stat stat : this.baseStats) { 
    		if (stat.getName().equals(name)) {
    			res += stat.getValue();
    		}
    	}
    	
    	for( Slot slot : this.inventory ) {
    		res += slot.getStat(name);
    	}
    	return res;
    }
   
    
    public List<Item> getEquippedItems() { return equippedItems; }
}
