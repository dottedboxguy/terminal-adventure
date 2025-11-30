package exceptions;

public class invalidInputException extends terminalAdventureException {
	public invalidInputException(String errorMessage) {
		super(errorMessage);
	}
}
