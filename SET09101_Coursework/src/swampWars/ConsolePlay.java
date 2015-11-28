package swampWars;

import java.util.Scanner;

import swampWars.control.GameControl;

public class ConsolePlay {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean play = true;
		while (play) {
			System.out.println("Welcome to:\nSWAMP WARS!\n");
			System.out.println("Would you like to start a new Game?\ny/n");
			String choice = scanner.nextLine();
			if (choice.equals("y")) {
				newGame();
			} else {
				play = (!play);
			}
		}
		System.out.println("Thanks for playing!");
		System.exit(0);

	}

	private static void newGame() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		System.out.println("Please enter a name for your Ogre:");
		String ogreName = scanner.next();
		System.out.println("Please enter the size of your swamp(a number between 4 - 10):");
		int size = scanner.nextInt();
		if (!(size <= 4) || !(size >= 10)) {
			GameControl.setGRIDSIZE(size - 1);
		} else {
			GameControl.setGRIDSIZE(3);
		}

		System.out.println(
				"A new game is starting\nType move to move!\nType undo to go back a turn!\nType redo to go forward a turn!\nType diet to change "
						+ ogreName + "'s Diet!\nType quit to Quit!");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		GameControl gc = new GameControl(ogreName);
		gc.display();
		while (!gc.isGameOgre()) {
			String command = scanner.next();
			System.out.println("");
			if (command.equals("move")) {
				gc.nextTurn();
			} else if (command.equals("undo")) {
				gc.undo();
			} else if (command.equals("redo")) {
				gc.redo();
			} else if (command.equals("diet")) {
				System.out.print(ogreName + " says:\n'");
				gc.getCurrentState().getPlayer().getDiet().Diet();
				System.out.print("'\n");
				System.out.println("Type 0 to eat Enemies!\nType 1 to eat Knights!\nType 2 to eat Very-Big-Macs!");
				int choice = scanner.nextInt();
				if (choice >= 0 && choice <= 2) {
					gc.getCurrentState().getPlayer().updateDiet(choice);
					gc.getCurrentState().getPlayer().getDiet().Diet();
					System.out.println("\n");
				} else {
					System.out.println("Invalid command. Pleas try again.");
				}
			} else if (command.equals("quit")) {
				break;
			} else {
				System.out.println("Command not recognized. Pleas try another.");
			}
			gc.display();
		}
		gc.gameOver();
		System.out.println("\n");

	}
}
