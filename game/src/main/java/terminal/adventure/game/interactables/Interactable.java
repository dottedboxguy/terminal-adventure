package terminal.adventure.game.interactables;

import terminal.adventure.game.Lookable;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;

public interface Interactable extends Lookable{

	public String action(Actor actor);

	public String actionWithItem(Actor actor, Item item);

}