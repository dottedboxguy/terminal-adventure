package terminal.adventure.game.characters;

import terminal.adventure.game.Location;

public class Player extends Character {

    private Location currentLocation;

    public Player(String name, Location start) {
        //to do 
        this.currentLocation = start;
    }

    public Location getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(Location loc) { currentLocation = loc; }

    @Override
    public void takeTurn(Player player) {
        // Players don't have automated turns
    }
}
