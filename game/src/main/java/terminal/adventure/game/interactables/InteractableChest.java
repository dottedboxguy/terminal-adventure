package terminal.adventure.game.interactables;

import terminal.adventure.game.inventory.Inventory;

public class InteractableChest extends Interactable{

    private boolean isOpened = false;
	public InteractableChest(String description, String name, Inventory inventory){
		super(description, name, inventory);
	}
    
    @Override
	public void action(){
        this.isOpened = true;
    };

}