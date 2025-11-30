package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.game.Console;
import terminal.adventure.game.inventory.items.Item;


public class CommandTake implements Command {

    @Override
    public void execute(String[] args, Console console) {
        List<Item> itemsToTake = console.getPlayer().getActor().getCurrentLocation().getInventory().getItems();
        for (String arg : args) {
            for (Item item : itemsToTake) {
                if (arg.equals(item.getName())) {
                    console.getPlayer().getActor().getInventory().add(item);
                    console.getPlayer().getActor().getCurrentLocation().getInventory().remove(item);
                }
            }
        }
        
    }
    
    @Override
    public String help() {
        return "\n Usage : \n LOOK \t ";
    }
}