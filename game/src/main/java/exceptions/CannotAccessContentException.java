package exceptions;

public class CannotAccessContentException extends terminalAdventureException {
	public CannotAccessContentException(String errorMessage) {
		super(errorMessage);
	}
}