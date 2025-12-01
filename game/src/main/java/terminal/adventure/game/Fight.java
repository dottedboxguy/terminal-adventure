package terminal.adventure.game;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.actors.Actor;

public class Fight {

	List<Actor> fighters;
		
	public Fight() {
		this.fighters = new ArrayList<>();
	}

	public void addFighter(Actor c) {
		fighters.add(c);
	}
	
	public void removeFighter(Actor c) {
		fighters.remove(c);
	}
	

	public List<Actor> getFightersByFaction(Faction fac) {
		List<Actor> res = new ArrayList<>();
		
		for (Actor f : fighters) {
			if (f.getController().getFaction() == fac) {
				res.add(f);
			}
		}
		
		return res;
	}

	
	public static void main(String args[]) {
		
		Actor a1 = new terminal.adventure.game.actors.Goblin("bob");
		Actor a2 = new terminal.adventure.game.actors.Goblin("bill");
		
		Controller c1 = new terminal.adventure.game.controllers.AIController(a1, terminal.adventure.game.controllers.Faction.badGuys);
		Controller c2 = new terminal.adventure.game.controllers.AIController(a2, terminal.adventure.game.controllers.Faction.goodGuys);
		
		Fight fight = new Fight();
		
		fight.addFighter(c1);
		fight.addFighter(c2);	
	}
}
