package terminal.adventure.game;

public class Stats {

	private int strength;
	private int speed;
	private int armor;
	private int currentHealth;
	private int maxHealth;
	//...
	
	public Stats() {
		this.strength = 0;
		this.speed = 0;
		this.armor = 0;
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
	
	public Stats statsSum(Stats other) {
		
		Stats res = new Stats();
		
		res.setStrength(this.strength + other.getStrength());
		res.setSpeed(this.speed + other.getSpeed());
		res.setArmor(this.strength + other.getStrength());		
		
		return res;
	}

}
