package terminal.adventure.game.actors;

public class Goblin extends Actor{
    
    public Goblin(String name, String description){
        super(name, description);
        this.baseStats.setMaxHealth(40);
        this.baseStats.setCurrentHealth(40);
        this.baseStats.setStrength(3);
    }
    
}