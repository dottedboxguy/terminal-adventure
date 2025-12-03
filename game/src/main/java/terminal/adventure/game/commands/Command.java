package terminal.adventure.game.commands;

import terminal.adventure.exceptions.invalidInputException;
import terminal.adventure.game.actors.Actor;

public abstract  class Command {
	protected String[] args;
	protected boolean isTerminal;
	public Command(String[] args, boolean isTerminal){
		this.isTerminal = isTerminal;
		this.args = args;
	}
	public abstract String execute(Actor actor) throws invalidInputException;
	public abstract String help();
	public boolean isTerminal(){
		return this.isTerminal;
	}
}
