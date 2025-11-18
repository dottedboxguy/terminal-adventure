package terminal.adventure.game;

public abstract class Interactable {

	public final String description;
	public final String name;

	public Interactable(String desc, String name){
		this.description = desc;
		this.name = name;
	}

	public abstract void action();

}