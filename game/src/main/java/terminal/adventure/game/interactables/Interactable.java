package terminal.adventure.game.interactables;

public abstract class Interactable{

	private final String DESCRIPTION;
	private final String name;

	public Interactable(String description, String name){
		this.DESCRIPTION = description;
		this.name = name;
	}

	public abstract void action();

	public String getDescription(){
		return this.DESCRIPTION;
	}

	public String getName(){
		return this.name;
	} 

}