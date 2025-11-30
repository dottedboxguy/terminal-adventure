package terminal.adventure.game.characters;

public class Human extends Character {

    public Human(String name, String description){
        super(name, description);
        this.baseStats.setMaxHealth(100);
        this.baseStats.setCurrentHealth(100);
        this.baseStats.setStrength(5);
    }

}