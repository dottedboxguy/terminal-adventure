package terminal.adventure.game.commands;

import java.util.List;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.Stats;
import terminal.adventure.game.actors.Actor;

public class CommandAttack extends Command{

	public CommandAttack(String[] args) {
		super(args, true);
	}

	@Override
	public String execute(Actor actor) throws invalidInputException {

		if (args.length == 0) {
			return this.help();
		}
		
		List<Actor> targets = actor.getCurrentLocation().getActorByName(args[0]);
		
		if (targets.size() == 0) {
			return "There is no one with that name here...\n";
		}
		
		Actor target = targets.get(0);
		
		Stats ret = actor.attack(target);
		
		
		String res = "You dealt "+ret.getStrength()+" damage to "+target.getName()+".\n";
		
		if (ret.getCurrentHealth() <= 0 ) {
			res+= "You killed"+target.getName()+".\n";
		} else {
			res+= ret.getCurrentHealth() + "HP remaining.\n";
		}

				
		return res;

	}

	@Override
	public String help() {
		return "who do you want to ATTACK ?\nUsage : ATTACK <target name>\n";
	}

}
