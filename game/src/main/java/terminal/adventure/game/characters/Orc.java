package terminal.adventure.game.characters;

public class Orc extends Character{
    
    public Orc(String name){
        super(name);
        this.baseStats.setMaxHealth(250);
        this.baseStats.setCurrentHealth(250);
        this.baseStats.setArmor(10);
        this.baseStats.setStrength(20);
    }
    
}