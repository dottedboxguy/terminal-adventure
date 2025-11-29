package terminal.adventure.game.exits;
import java.util.function.BooleanSupplier;

public class ConditionalExit extends Exit {
    private BooleanSupplier condition;
    private String message;

    public ConditionalExit(Location destination, BooleanSupplier condition, String message) {
        super(destination);
        this.condition = condition;
        this.message = message;
    }

    @Override
    public boolean canCross(Player player) {
        return condition.getAsBoolean();
    }

    @Override
    public String getFailMessage() {
        return message;
    }
}
