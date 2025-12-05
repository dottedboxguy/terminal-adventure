package terminal.adventure.game;

public class Stats {

	private int strength;
	private int speed;
	private int armor;
	private int currentHealth;
	private int maxHealth;
	
	public Stats() {
		this.strength = 0;
		this.speed = 0;
		this.armor = 0;
		this.currentHealth = 0;
		this.maxHealth = 0;
	}
	
	/**
	 * @return int Strength Stat.
	 */
	public int getStrength() {
		return strength;
	}
	/**
	 * @param The new Strength stat.
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return int Speed Stat.
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * @param The new Speed stat.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return int Armor Stat.
	 */
	public int getArmor() {
		return armor;
	}
	/**
	 * @param The new Armor stat.
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}

	/**
	 * @return int Max Health Stat.
	 */
	public int getMaxHealth() {
		return this.maxHealth;
	}
	/**
	 * @param The new Max Health stat.
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	/**
	 * @return int Current Health Stat.
	 */
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	/**
	 * @param The new Current Health stat.
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	/**
	 * Reduces current health by the specified damage amount.
	 * If health reaches 0 or below, the character should die.
	 * 
	 * @param damage the amount of damage to remove from current health
	 * @return true if the character's health is at 0 or below (should die),
	 *         false if the character is still alive
	 */
	public boolean loseHealth(int damage) {
		if (damage < 0) {
			throw new IllegalArgumentException("Damage cannot be negative: " + damage);
		}
		
		this.currentHealth -= damage;
		
		// Prevent health from going below 0
		if (this.currentHealth < 0) {
			this.currentHealth = 0;
		}
		
		// Return whether character should die
		return this.currentHealth <= 0;
	}
	
	/**
	 * Recover Current Health points.
	 * @param heal the amount of points to recover.
	 */
	public void gainHealth(int heal) {
		if (heal > 0) {
			this.currentHealth += heal;
			if (this.currentHealth < this.maxHealth){
				this.currentHealth = this.maxHealth;
			}
		}
	}

	/**
	 * Adds up this Stats's values with another one
	 * @param other the other Stats object to add up
	 * @return the addition result
	 */
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
