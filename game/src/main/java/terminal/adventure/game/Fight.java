package terminal.adventure.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.characters.Character;

public class Fight {

	List<Controller> allies;
	List<Controller> ennemies;
	
	
	Queue<Controller> turns;
	
	public Fight(List<Controller> allies, List<Controller> ennemies) {
		this.allies = allies;		
		this.ennemies = ennemies;
		
		turns = new LinkedList<>();
		
		turns.addAll(this.allies);
		turns.addAll(this.ennemies);
	}

	public void start() {
		
		while( ennemies.size() > 0 && allies.size() > 0) {
			Controller current = turns.poll();
			
			current.FightTurn(this);
			
			turns.add(current);
		}
		
	}

	public void removeEnnemy(Controller c ) {
		ennemies.remove(c);
	}
	
	public List<Controller> getAllies() {
		return allies;
	}

	public void setAllies(List<Controller> allies) {
		this.allies = allies;
	}

	public List<Controller> getEnnemies() {
		
		return ennemies;
	}

	public void setEnnemies(List<Controller> ennemies) {
		this.ennemies = ennemies;
	}
	
	public static void main(String args[]) {

		Character c1 = new terminal.adventure.game.characters.Goblin("bob");
		Character c2 = new terminal.adventure.game.characters.Goblin("bill");
		
		List<Controller> ops = new ArrayList<>();
		List<Controller> allies = new ArrayList<>();
		
		ops.add(new terminal.adventure.game.controllers.AIController(c2));
		allies.add(new terminal.adventure.game.controllers.AIController(c1));
	
		Fight f = new Fight(allies, ops);
		f.start();
		
		
		
	}

	
}
