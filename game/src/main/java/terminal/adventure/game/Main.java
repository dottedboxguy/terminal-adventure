package terminal.adventure.game;

import terminal.adventure.game.gamestates.BloodCultScenario;
import terminal.adventure.game.gamestates.GameState;


public class Main {

	public static void main(String args[]) {

		GameState game = new BloodCultScenario();

		game.play();
	}
	
}