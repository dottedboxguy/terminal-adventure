package terminal.adventure.game.commands;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;

/**
 * Abstract class representing a console command in the adventure game.
 * 
 * This class defines the basic structure that all concrete commands must implement.
 * It encapsulates command arguments and indicates whether its execution should terminate the game.
 * 
 * Examples of concrete implementations: LookCommand, GoCommand,
 * UseCommand, etc.
 * 
 */
public abstract class Command {
    
    /**
     * Arguments passed to the command upon execution.
     */
    protected String[] args;
    
    /**
     * Indicates whether executing this command should terminate the player's turn.
     * 
     * If true, the player will pass his turn after executing this command.
     * 
     */
    protected boolean isTerminal;
    
    /**
     * Protected constructor to initialize a new command.
     * 
     * Only subclasses can instantiate this class via this constructor.
     *
     * @param args      Array of strings containing the command arguments.
     *                  Must not be null but may be empty.
     * @param isTerminal Indicates whether the command terminates the player's turn.
     */
    public Command(String[] args, boolean isTerminal) {
        this.isTerminal = isTerminal;
        this.args = args;
    }
    
    /**
     * Executes the command.
     * 
     * Each subclass must provide its specific implementation of this method.
     * The method should validate arguments and perform the corresponding action.
     *
     * @param actor The actor (player) executing the command.
     *              Must not be null.
     * @return The execution result as a string to be displayed to the user.
     * @throws invalidInputException If arguments are invalid, missing,
     *                               or if the command cannot be executed in
     *                               the current context.
     */
    public abstract String execute(Actor actor) throws invalidInputException;
    
    /**
     * Returns the help text for this command.
     * 
     * This method is typically invoked by the HELP command to explain
     * the usage of each available command.
     *
     * @return A string describing the command's syntax and usage.
     */
    public abstract String help();
    
    /**
     * Checks if this command is a terminal command.
     * 
     * A terminal command is one whose execution causes the player's turn to end
     *
     * @return true if the command terminates the turn, false otherwise.
     */
    public boolean isTerminal() {
        return this.isTerminal;
    }
}