package terminal.adventure.game;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import terminal.adventure.game.Location;
import terminal.adventure.game.controllers.Controller;
import terminal.adventure.game.controllers.Faction;
import terminal.adventure.game.controllers.PlayerController;
import terminal.adventure.game.gamestates.GameState;
import terminal.adventure.game.Console;

public class GameStateTest {

    private static class TestGameState extends GameState {
        public TestGameState(Queue<Controller> controllers, List<Location> map) {
            super(controllers, map);
        }
    }
    
    @Test
    public void testPlayWithDeadPlayerController() {
        Queue<Controller> controllers = new LinkedList<>();
        List<Location> map = new LinkedList<>();
        PlayerController playerController = new PlayerController(Faction.goodGuys, new Console());
        controllers.add(playerController);
        GameState gameState = new TestGameState(controllers, map);
        playerController.die();
        gameState.play();
        assertTrue(GameState.loseCondition);
    }


    @Test(expected = FileNotFoundException.class)
    public void testSaveInFileWithInvalidPath() throws IOException {
        Queue<Controller> controllers = new LinkedList<>();
        List<Location> map = new LinkedList<>();
        GameState gameState = new TestGameState(controllers, map);
        GameState.saveInFile(gameState, "/invalid/path/save.tas");
    }

    @Test(expected = java.io.FileNotFoundException.class)
    public void testLoadFromFileWithInvalidPath() throws IOException, ClassNotFoundException {
        GameState.loadFromFile("/invalid/path/save.tas");
    }
}
