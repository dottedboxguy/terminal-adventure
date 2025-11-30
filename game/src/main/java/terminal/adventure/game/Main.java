package terminal.adventure.game;
import java.util.Scanner;

public class Main {

	public static void main(String args[]){

        boolean solved = false;
        Scanner scanner = new Scanner(System.in);
        while (!solved) {
			console.runCommand(scanner.nextLine());
			solved = true;
		}
    }
}