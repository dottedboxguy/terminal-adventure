package terminal.adventure.game.actors;

public class Human extends Actor {

    public Human(String name, String description){
        super(name, description);
        this.baseStats.setMaxHealth(100);
        this.baseStats.setCurrentHealth(100);
        this.baseStats.setStrength(5);
    }

}