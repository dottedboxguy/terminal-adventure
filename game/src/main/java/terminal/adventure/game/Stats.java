package terminal.adventure.game;

public class Stats {

	private int strength;
	private int speed;
	private int armor;
	//...
	
	public Stats() {
		this.strength = 0;
		this.speed = 0;
		this.armor = 0;
	}
	
	public Stats(int strength, int speed, int armor) {
		this.strength = strength;
		this.speed = speed;
		this.armor = armor;
	}
	
	
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}

}
