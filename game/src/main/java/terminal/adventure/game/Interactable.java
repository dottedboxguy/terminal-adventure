package terminal.adventure.game;

public abstract class Interactable implements Description{

	public final String description;
	public final String name;

	public Interactable(String desc, String name){
		this.description = desc;
		this.name = name;
	}

	public abstract void action();

	public String getDescription(){
		return this.description;
	}

	public String getName(){
		return this.name;
	}

}