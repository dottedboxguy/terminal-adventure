package terminal.adventure.game.characters;

public class Goblin extends Character{
    
    public Goblin(String name){
        super(name, "A green filthy creature. Ew.");
        this.baseStats.setMaxHealth(40);
        this.baseStats.setCurrentHealth(40);
        this.baseStats.setStrength(3);
    }
    
}