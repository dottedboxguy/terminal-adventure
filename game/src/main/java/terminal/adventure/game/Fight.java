package terminal.adventure.game;

import java.util.ArrayList;
import java.util.List;

import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.actors.Actor;

public class Fight {

	List<Actor> fighters;
		
	public Fight() {
		this.fighters = new ArrayList<>();
	}

	
	/**
	 * Adds an Actor to the Fight.
	 * Warning : the fight will not be added to the Actor.
	 * Will not do anything if the Actor is already in the fight.
	 * @param c The Actor to add
	 */
	public void addFighter(Actor c) {
		if (!fighters.contains(c)) {			
			fighters.add(c);
		}
	}
	
	
	/**
	 * Removes an Actor from the Fight.
	 * Warning : the fight will not be removed from the Actor.
	 * Will not do anything if the Actor isn't already in the fight.
	 * @param c The Actor to remove.
	 */
	public void removeFighter(Actor c) {
		fighters.remove(c);
	}
	
	/**
	 * Returns every Actor in the fight that belongs to the specified Faction.
	 * @param fac The faction to look for
	 * @return A list of Actors that belongs to the faction.
	 */
	public List<Actor> getFightersByFaction(Faction fac) {
		List<Actor> res = new ArrayList<>();
		
		for (Actor f : fighters) {
			if (f.getController().getFaction() == fac) {
				res.add(f);
			}
		}
		
		return res;
	}

	/**
	 * Returns every Actor in the fight that belongs all but the specified Faction..
	 * @param fac The faction to exclude
	 * @return A list of Actors that belongs to all the other Factions.
	 */
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
