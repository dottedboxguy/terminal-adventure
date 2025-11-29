package terminal.adventure.game;



public class LockedExit extends Exit {
    private String requiredKey;

    public LockedExit(Location destination, String requiredKey) {
        super(destination);
        this.requiredKey = requiredKey;
    }

    @Override
    public boolean canCross(Player player) {
        return player.hasItem(requiredKey);
    }

    @Override
    public String getFailMessage() {
        return "The door is locked. You need the " + requiredKey + ".";
    }
}
