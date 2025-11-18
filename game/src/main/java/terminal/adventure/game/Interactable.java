package terminal.adventure.game;

public abstract class Interactable implements Description{

	private final String description;
	private final String name;

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