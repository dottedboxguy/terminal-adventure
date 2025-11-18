package terminal.adventure.game;

public class Slot {

	private Item content;
	public final Class type;

	/**
	 * Constructor of Slot
	 * @param type : Type of the slot
	 */
	public Slot(String type) {
		this.type = type.getClass();
		this.content = null;
	}

	/**
	 * Check if an item can be equipped in the slot
	 * @param Item : Item to equip
	 * @return boolean : true if the item can be equipped, false otherwise
	 */
	public bool canEquip(int Item) {
		
	}

	public Item unequp() {
		// TODO - implement Slot.unequp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Item
	 */
	public Item equip(int Item) {
		// TODO - implement Slot.equip
		throw new UnsupportedOperationException();
	}

}