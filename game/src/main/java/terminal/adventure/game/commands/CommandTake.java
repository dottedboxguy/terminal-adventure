package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Console;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.inventory.items.Item;


public class CommandTake extends Command {

	
	/*
    @Override
    public void execute(String[] args, Console console) {
        if (args.length == 0){

        }
        else{
            if (args.length == 1 && "all".equals(args[0])){
                console.getPlayer().getActor().getInventory().takeAll(console.getPlayer().getActor().getCurrentLocation().getInventory());
            }
            else{
                for (String arg : args){
                    List<Item> itemsToAdd = console.getPlayer().getActor().getCurrentLocation().getInventory().searchItemsByName(arg);
                    for (Item item : itemsToAdd) {
                        console.getPlayer().getActor().getInventory().takeItem(console.getPlayer().getActor().getCurrentLocation().getInventory(), item);
                    }
                }
            }
        }
    }
    */
	
    public CommandTake(String[] args) {
		super(args, true);
	}

	@Override
    public String help() {
        return "\n Usage : \n TAKE \t all \n\t <arg1> <arg2> ...";
    }

	@Override
	public String execute(Actor actor) throws invalidInputException {
		//TODO
		return null;
	}
}