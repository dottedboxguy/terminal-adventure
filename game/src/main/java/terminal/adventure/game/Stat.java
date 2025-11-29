package terminal.adventure.game;

public class Stat {
    private String name;
    private int value;

    public Stat(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // Getters / Setters
    public String getName() { return name; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}
