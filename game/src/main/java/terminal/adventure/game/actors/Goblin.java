package terminal.adventure.game.actors;

public class Goblin extends Actor{
    
    public Goblin(String name){
        super(name, "A green filthy creature. Ew.");
        this.baseStats.setMaxHealth(40);
        this.baseStats.setCurrentHealth(40);
        this.baseStats.setStrength(3);
    }
    
}