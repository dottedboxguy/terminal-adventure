package exceptions;

public class CharacterShouldDieException extends terminalAdventureException {
	public CharacterShouldDieException(String errorMessage) {
		super(errorMessage);
	}
}
