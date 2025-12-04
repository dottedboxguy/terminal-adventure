package terminal.adventure.game.interactables;

import terminal.adventure.game.inventory.Inventory;

public class InteractableChest extends Interactable{
    private Inventory inventory;
    private boolean isOpened = false;
	public InteractableChest(String description, String name, Inventory inventory){
		super(description, name);
	}
    
    @Override
    /**
     * see {@link Interactable}
     */
	public void action(){
        this.isOpened = true;
    };

}