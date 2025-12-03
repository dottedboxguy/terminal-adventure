package terminal.adventure.game.commands;

import terminal.adventure.game.actors.Actor;

public abstract class Command {
	
	protected CommandType type;
	protected String[] args;
	private String returnMessage;

	
	public Command(){}
	
	/**
	 * Returns a copy of this instance.
	 * @return
	 */
	public abstract Command copy();
	
	/**
	 * Called by the Controller if the Commands needs
	 * Actor information for its execution.
	 * Must modify the returnMessage, e.g if the target isn't found
	 * and/or subclass-specific attribute that will be accessed by the Controller.
	 * @param actor The Controller's binded actor, may be needed to use the Command
	 */
	public abstract void init(Actor actor);
	
	public CommandType getType() {
		return this.type;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}
	
	public String[] getArgs() {
		return this.args;
	}

	public String help() {
		return "<default help message>";
	}
	
	public String getReturnMessage() {
		return this.returnMessage;
	}
	
	public void setReturnMessage(String msg) {
		this.returnMessage = msg;
	}
	
}
