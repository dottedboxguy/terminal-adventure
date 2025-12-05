package terminal.adventure.game.inventory.items;

import terminal.adventure.game.Stats;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.gamestates.GameState;
import terminal.adventure.game.interactables.Interactable;

public class WinGem extends Item implements Interactable {
    
    public WinGem(String name, String description, Stats stats) {
		super(name, description, stats);
	}

    @Override
    public String action(Actor actor) {
        GameState.winCondition = true;
        return "Congratulations! By interacting with the gem you restored water to the kingdom!";
    }

    @Override
    public String actionWithItem(Actor actor, Item item) {
        return "That won't work";
    }

}
