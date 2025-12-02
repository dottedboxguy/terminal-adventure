package terminal.adventure.game;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.controllers.AIController;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.actors.*;


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
		
		Actor a1 = new Goblin("bob");
		Actor a2 = new Goblin("bill");
		
		Controller c1 = new AIController(Faction.badGuys);
		Controller c2 = new AIController(Faction.goodGuys);
		c1.bindActor(a1);
		c2.bindActor(a2);
		
		
		Queue<Controller> turns = new LinkedList<>();
		turns.add(c1);
		turns.add(c2);
		
		//  --- 
		
		Fight fight = new Fight();
		
		a1.enterFight(fight);
		a2.enterFight(fight);
		
				
		while (turns.size() == 2) { // "main" loop
			Controller c = turns.poll();
			
			if (c.isDead()) {
				continue;
			}
			
			System.out.println("It's "+ c.getActor().NAME+" turn");

			c.playTurn();


			turns.add(c);
		}
	}
}