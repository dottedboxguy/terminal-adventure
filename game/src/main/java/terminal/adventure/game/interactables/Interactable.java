package terminal.adventure.game.interactables;

import terminal.adventure.game.Lookable;
import terminal.adventure.game.actors.Actor;

public abstract class Interactable implements Lookable{

	private final String DESCRIPTION;
	private final String name;

	public Interactable(String description, String name){
		this.DESCRIPTION = description;
		this.name = name;
	}

	/**
	 * The process in case the interactable object is used.
	 */
	public abstract void action(Actor actor);

	@Override
	/**
	 * see {@link Lookable}
	 */
	public String look(){
		return this.DESCRIPTION;
	}

	/**
	 * @return this Object's name.
	 */
	public String getName(){
		return this.name;
	} 

}
//TODO : why not an interface ?