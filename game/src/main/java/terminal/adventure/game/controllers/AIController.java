package terminal.adventure.game.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import terminal.adventure.game.Stats;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.exits.Exit;

public class AIController extends Controller {

	Random random = new Random();
	
	public AIController(Actor actor, Faction faction) {
		super(actor, faction);
	}
	
	public AIController(Faction faction) {
		super(faction);
	}



	@Override
	public int equipChooseSlot(List<String> candidates) {
		int emptySlotIndex = candidates.indexOf(null);
		if (emptySlotIndex != -1) {
			return emptySlotIndex;
		}
		else {
			return 0;
		}
	}



	@Override
	public void play() {
	
		//System.out.println("DEBUG : AIController.java play()");
		//System.out.println(this.actor);
		
		if (this.actor.getFight() != null) {

			Actor target = this.actor.getFight().getFightersByAntiFaction(this.getFaction()).get(0);
			
			this.actor.attack(target);
		} else {
		
			//System.out.println("Location :"+this.actor.getCurrentLocation());
			
			Collection<Exit> exits = this.actor.getCurrentLocation().getVisibleExits().values();
			
			
			int nExits = exits.size();
			if ( nExits > 0) {
				
				int e = random.nextInt( nExits );
				
				//this.actor.move(exits[e});
				
			}
			
			System.out.println("DEBUG:play : "+ this.actor.getName() +" . "+this.actor.getCurrentLocation().getName());
			System.out.println(this.actor.getCurrentLocation().getActors());
			
		}
		
		System.out.println("End Turn.");
	}

	@Override
	public void takeAttackReport(Stats report) {/*foo*/}
}