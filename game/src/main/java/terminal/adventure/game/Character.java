package terminal.adventure.game;

public class Character {

	private Slot inventory;
	private Location position;
	private int hp;
	private int mp;
	private int armor;

	/**
	 * 
	 * @param hp
	 * @param mp
	 * @param armor
	 */
	public Character(int hp, int mp, int armor) {
		this.hp = hp;
		this.mp = mp;
		this.armor = armor;
		// to do the rest
		throw new UnsupportedOperationException();
	}

}