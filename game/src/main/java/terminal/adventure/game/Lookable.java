package terminal.adventure.game;

/**
 * An abstract method to apply to everything that can be looked, to provide
 * a specific description.
 */
public interface Lookable{
	
	/**
	 * @return the object's description
	 */
	public abstract String look();
}