package terminal.adventure.game;

public class HiddenExit extends Exit {

	private boolean isHidden;

	/**
	 * 
	 * @param dest
	 */
	public HiddenExit(Location dest){
		super(dest);
		this.isHidden = true;
	}

	public void unveil() {
		this.isHidden = false;
	}

	public boolean isHidden(){
		return this.isHidden;
	}

}