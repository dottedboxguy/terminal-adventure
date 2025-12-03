package terminal.adventure.game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.actors.Cultist;
import terminal.adventure.game.actors.Goblin;
import terminal.adventure.game.actors.Human;
import terminal.adventure.game.controllers.AIController;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.controllers.PlayerController;
import terminal.adventure.game.exits.Exit;
import terminal.adventure.game.exits.HiddenExit;
import terminal.adventure.game.exits.OpenExit;


public class Main {

	public static void main2(String args[]){

        boolean solved = false;
        Scanner scanner = new Scanner(System.in);
        while (!solved) {
			//console.runCommand(scanner.nextLine());
			solved = true;
		}
    }
	
	
	public static void main(String args[]) {
		
		
		// -------------- init ----------------
		
		/*
		Actor a1 = new Goblin("bob");
		Actor a2 = new Goblin("bill");
		
		Controller c1 = new PlayerController(Faction.badGuys);
		Controller c2 = new AIController(Faction.goodGuys);
		c1.bindActor(a1);
		c2.bindActor(a2);
		*/
		boolean winCondition = false;
		boolean loseCondition = false;
		
		Queue<Controller> turns = new LinkedList<>();

		List<Location> locs = init(turns);
		
		//  --- 
		
				
		while (!winCondition && !loseCondition ) { // "main" loop
			Controller c = turns.poll();
			
			if (c.isDead()) {
				continue;
			}
			
			System.out.println("It's "+ c.getActor().NAME+" turn");

			c.playTurn();

			turns.add(c);
		}
	}
	
	private static List<Location> init(Queue<Controller> turns) {
		
		Console console = new Console();
		
		List<Location> locs= new ArrayList<>();
		Actor tempActor;
		Controller tempController;
		
		locs.add( new Location("Dark Room", "A dark cellar with basic furniture scattered across the floor. A thin ray of light is piercing through the roof.") );
		locs.add( new Location("Chest Room", "A small circular room, in the center lies an old chest."));
		locs.add( new Location("Hole of Shame", "A simple hole, just too deep to climb back up.\nYou have a hunch there's a way to get out, but you're on your own"));
		locs.add( new Location("Ritual Altar", "A large room circled by large marble pillars.\nThe floor is covered by several layers of all shades of red. It seems to be coming from a majestuous altar in the center."));
		
		locs.get(0).addExit("Chest Room", new OpenExit(locs.get(1), "Ruined Door", "A small wooden door, barely holding."));
		locs.get(0).addExit("Hole", new OpenExit(locs.get(2), "Floor Hole", "Just a hole in the ground. You think you could slip in."));
		locs.get(2).addExit("Wall crack", new HiddenExit(locs.get(3),"Wall crack", "A crack barely visible in the Wall. You think you could crawl through."));
		
		
		// Adding one Actor + Controller to the game
		tempActor = new Cultist("Small cultist");
		tempController = new AIController(Faction.badGuys);
		tempController.bindActor(tempActor);
		
		locs.get(3).addActor(tempActor);
		turns.add(tempController);
		//
		
		// Adding one Actor + Controller to the game
		tempActor = new Cultist("Tall cultist");
		tempController = new AIController(Faction.badGuys);
		tempController.bindActor(tempActor);
		
		locs.get(3).addActor(tempActor);
		turns.add(tempController);
		//
		
		// Adding Player Actor + Controller to the game
		tempActor = new Human("You", "Hero of the kingdom, you're determined to kill the blood cultists");
		tempController = new PlayerController(Faction.goodGuys, console);
		tempController.bindActor(tempActor);
		
		locs.get(0).addActor(tempActor);
		turns.add(tempController);
		//
		
		
		return locs;
	}
	
	
	
}