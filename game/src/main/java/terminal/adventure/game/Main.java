package terminal.adventure.game;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import terminal.adventure.exceptions.actorlessControllerException;
import terminal.adventure.game.actors.Actor;
import terminal.adventure.game.controllers.Controller;

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
		
		Actor a1 = new terminal.adventure.game.actors.Goblin("bob");
		Actor a2 = new terminal.adventure.game.actors.Goblin("bill");
		
		Controller c1 = new terminal.adventure.game.controllers.AIController(terminal.adventure.game.controllers.Faction.badGuys);
		Controller c2 = new terminal.adventure.game.controllers.AIController(terminal.adventure.game.controllers.Faction.goodGuys);
		 
		Queue<Controller> turns = new LinkedList<>();
		turns.add(c1);
		turns.add(c2);
		
		
		
		c1.bindActor(a1);
		c2.bindActor(a2);
		
		Fight fight = new Fight();
		
		a1.enterFight(fight);
		a2.enterFight(fight);
		
		
		while (turns.size() == 2) {
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