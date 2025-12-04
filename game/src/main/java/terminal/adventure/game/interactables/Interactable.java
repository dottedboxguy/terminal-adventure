package terminal.adventure.game.interactables;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;

public interface Interactable {

	public void action(Actor actor);

	public void actionWithItem(Actor actor, Item item);

}