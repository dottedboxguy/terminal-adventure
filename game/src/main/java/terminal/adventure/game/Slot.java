package terminal.adventure.game;

public class Slot {

	private Item content = null;
	public final Class<?> type;

	public Slot(Class<?> type) {
		this.type = type;
	}

	public boolean canEquip(int Item) {
		// TODO - implement Slot.canEquip
		throw new UnsupportedOperationException();
	}

	public Item unequp() {
		// TODO - implement Slot.unequp
		throw new UnsupportedOperationException();
	}

	public Item equip(int Item) {
		// TODO - implement Slot.equip
		throw new UnsupportedOperationException();
	}

	public int getStat(String name) {
		if (this.content != null) {
			return this.content.getStat(name);
		}
		return 0;
	}

}