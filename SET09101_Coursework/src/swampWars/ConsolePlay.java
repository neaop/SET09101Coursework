package swampWars;

import java.util.Scanner;

import swampWars.actors.*;
import swampWars.control.GameControl;

public class ConsolePlay {

	private static String ogreName;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean play = true;
		while (play) {

			System.out.println("Welcome to:\nSWAMP WARS!\n");
			System.out.println(
					"Would you like to start a new Game?\nType start to begin a new game!\nType exit to Exit.");
			String choice = scanner.nextLine();
			if (choice.equals("start")) {
				clearScreen();
				newGame();
			} else if (choice.equals("exit")) {
				play = (!play);
			} else {
				System.out.println("Command not recognized.");
			}
		}
		System.out.println("Thanks for playing!");
		System.exit(0);

	}

	@SuppressWarnings("resource")
	private static void newGame() {
		Scanner scanner = new Scanner(System.in);
		int xSize = 0;
		int ySize = 0;

		System.out.println("Please enter a name for your Ogre:");
		ogreName = scanner.nextLine();

		while ((xSize <= 4) || !(xSize >= 8)) {
			System.out.println("Please enter the width of your swamp(a number between 4 - 8):");
			String xString = scanner.next();
			try {
				xSize = Integer.parseInt(xString);
				break;
			} catch (NumberFormatException nfe) {

			}
		}
		GameControl.setX_SIZE(xSize - 1);

		while ((ySize <= 4) || !(ySize >= 8)) {
			System.out.println("Please enter the height of your swamp(a number between 4 - 8):");
			String yString = scanner.next();
			try {
				ySize = Integer.parseInt(yString);
				break;
			} catch (NumberFormatException nfe) {

			}
		}
		GameControl.setY_SIZE(ySize - 1);

		clearScreen();

		System.out.println("A new game is starting\nType help for command list:");
		printHelp();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		GameControl gc = new GameControl(ogreName);
		printTurn(gc);
		display(gc);
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
					System.out.println("\n");
					System.out.print(ogreName + " says:\n'");
					gc.getCurrentState().getPlayer().getDiet().Diet();
					System.out.print("'\n\n");
				} else {
					System.out.println("Invalid command. Pleas try again.");
				}

			} else if (command.equals("quit")) {
				break;
			} else if (command.equals("help")) {
				printHelp();
			} else {
				System.out.println("Command not recognized. Pleas try another.");
			}
			printTurn(gc);
			display(gc);
		}
		gc.gameOver();
		System.out.println("\n");
	}

	private static void printHelp() {
		System.out.println(
				"Type move to move!\nType undo to go back a turn!\nType redo to go forward a turn!\nType diet to change "
						+ ogreName + "'s Diet!\nType quit to Quit!\n");
	}

	private static void printTurn(GameControl gc) {
		System.out.println("Turn: " + gc.getTurnCount());
	}

	private static void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	private static void display(GameControl gc) {
		if (!gc.isGameOgre()) {
			for (int y = 0; y <= GameControl.getY_SIZE(); y++) {
				for (int x = 0; x <= GameControl.getX_SIZE(); x++) {
					System.out.print("[" + x + ", " + y);
					Ogre og = gc.getCurrentState().getPlayer();
					if (og.getXCoord() == x && og.getYCoord() == y) {
						System.out.print(" O");
					}
					for (SwampDenizen actor : gc.getCurrentState().getEnemyList()) {
						if (actor.getXCoord() == x && actor.getYCoord() == y) {
							System.out.print(" " + actor.getName().substring(0, 1));
						}
					}
					System.out.print("]");
				}
				System.out.print("\n");
			}
			System.out.print("\n");
		}
	}
}
