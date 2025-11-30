package terminal.adventure.game.interactables;

import exceptions.CannotAccessContentException;
import terminal.adventure.game.inventory.Inventory;

public class InteractableChest extends InteractableWithInventory{

    private boolean isOpened = false;
	public InteractableChest(String description, String name, Inventory inventory){
		super(description, name, inventory);
	}
    
    @Override
	public void action(){
        this.isOpened = true;
    };

    @Override
    public Inventory getInventory() throws CannotAccessContentException{
        if (isOpened){
            return this.inventory;
        }
        else{
            throw new CannotAccessContentException("Cannot Interact with the chest...\n It is closed!");
        }
    }

}