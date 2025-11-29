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

    public void addStat(Stat stat) {
        stats.add(stat);
    }

    // Getters
    public String getName() { return name; }
    public List<Stat> getStats() { return stats; }
}
