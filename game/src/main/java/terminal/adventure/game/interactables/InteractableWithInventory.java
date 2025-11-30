package terminal.adventure.game.interactables;

import exceptions.CannotAccessContentException;
import terminal.adventure.game.inventory.Inventory;

public abstract class InteractableWithInventory extends Interactable{

    protected Inventory inventory;
	public InteractableWithInventory(String description, String name, Inventory inventory){
		super(description, name);
        this.inventory = inventory;
	}
    
    public abstract Inventory getInventory() throws CannotAccessContentException;

}