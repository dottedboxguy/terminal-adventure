package terminal.adventure.game;

import exceptions.CharacterShouldDieException;

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

	public int getMaxHealth() {
		return this.maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	public void subtractHealth(int damage) throws CharacterShouldDieException {
		this.currentHealth -= damage;
		if (this.currentHealth < 0){
			throw new CharacterShouldDieException("character's health is dying");
		}
	}
	public void gainHealth(int heal){
		this.currentHealth += heal;
		if (this.currentHealth < this.maxHealth){
			this.currentHealth = this.maxHealth;
		}
	}

	public Stats statsSum(Stats other) {

		Stats res = new Stats();

		res.setStrength(this.strength + other.getStrength());
		res.setSpeed(this.speed + other.getSpeed());
		res.setArmor(this.strength + other.getStrength());
		res.gainHealth(other.getCurrentHealth());
		res.setMaxHealth(this.maxHealth + other.getMaxHealth());

		return res;
	}

}
