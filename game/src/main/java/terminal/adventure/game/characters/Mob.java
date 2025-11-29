package terminal.adventure.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Mob extends Character {

    private List<Item> loot;   // items dropped when mob is defeated
    private String description; // flavor text for the mob

    public Mob(String name, int maxHealth, int attackPower, int defense, String description) {
        super(name, maxHealth, attackPower, defense);
        this.loot = new ArrayList<>();
        this.description = description;
    }

    // Add item to the mob's loot
    public void addLoot(Item item) {
        loot.add(item);
    }

    // Get the loot dropped when the mob is defeated
    public List<Item> getLoot() {
        return loot;
    }

    // Get mob description
    public String getDescription() {
        return description;
    }

    // Simple AI: attack a target character
    public void attack(Character target) {
        if (!isAlive()) return;

        System.out.println(name + " attacks " + target.getName() + " for " + attackPower + " damage!");
        target.takeDamage(attackPower);

        if (!target.isAlive()) {
            System.out.println(target.getName() + " has been defeated by " + name + "!");
        }
    }

    @Override
    public void takeTurn(Character target) {
        // For now, simple AI: attack the given target
        attack(target);
    }
}
