package terminal.adventure.game.controllers;

import terminal.adventure.game.Location;
import terminal.adventure.game.Player;

public class PlayerController extends CharacterController {

    private Location currentLocation;

    public PlayerController(Player player, Location start) {
        super(player);
        this.currentLocation = start;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void moveTo(Location location) {
        this.currentLocation = location;
    }

    @Override
    public void takeTurn(GameState game, PlayerController playerController) {
        // Player does not act automatically
        // The main game loop handles input and actions.
    }
}
