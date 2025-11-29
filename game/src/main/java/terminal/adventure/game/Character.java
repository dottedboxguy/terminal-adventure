import java.util.ArrayList;
import java.util.List;

public class Character {
    private String name;
    private List<Stat> baseStats;
    private List<Item> equippedItems;

    public Character(String name) {
        this.name = name;
        this.baseStats = new ArrayList<>();
        this.equippedItems = new ArrayList<>();
    }

    public void addBaseStat(Stat stat) {
        baseStats.add(stat);
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
    public List<Item> getEquippedItems() { return equippedItems; }
}
