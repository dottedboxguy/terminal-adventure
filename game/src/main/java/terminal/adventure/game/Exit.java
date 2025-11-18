package terminal.adventure.game;

public class Exit {

	private Location destination;

	/**
	 * 
	 * @param dest
	 */
	public Exit(Location dest) {
		this.destination = dest;
	}

	public Location getDestination() {
		return this.destination;
	}

}