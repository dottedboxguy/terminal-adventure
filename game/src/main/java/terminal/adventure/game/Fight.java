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

	public List<Actor> getFightersByAntiFaction(Faction fac) {
		
		List<Actor> res = new ArrayList<>();
		
		for (Actor f : fighters) {
			if (f.getController().getFaction() != fac) {
				res.add(f);
			}
		}
		
		return res;
	}
	

}
