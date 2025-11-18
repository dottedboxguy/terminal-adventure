public abstract class Spell {

	private String name;
	private String description;

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	/**
	 * 
	 * @param target
	 */
	public void cast(Character target) {
		// TODO - implement Spell.cast
		throw new UnsupportedOperationException();
	}

}