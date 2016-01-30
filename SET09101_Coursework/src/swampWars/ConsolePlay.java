package swampWars;

import java.util.Scanner;

import swampWars.actors.*;
import swampWars.control.GameControl;

public class ConsolePlay {

	// string for player name
	private static String ogreName;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// declare varibles
		Scanner scanner = new Scanner(System.in);
		boolean play = true;

		// while play is not flipped
		while (play) {
			// ask player if they want to play
			System.out.println("Welcome to:\nSWAMP WARS!\n");
			System.out.println(
					"Would you like to start a new Game?\nType start to begin a new game!\nType exit to Exit.");

			String choice = scanner.nextLine();
			// if yes
			if (choice.equals("start")) {
				// start new game
				clearScreen();
				newGame();
				// if no
			} else if (choice.equals("exit")) {
				// flip play
				play = (!play);
				// else
			} else {
				// do nothing
				System.out.println("Command not recognized.");
			}
		}

		// exit game
		System.out.println("Thanks for playing!");
		System.exit(0);
	}

	// starts a new game
	@SuppressWarnings("resource")
	private static void newGame() {
		// declare variables
		Scanner scanner = new Scanner(System.in);
		int xSize = 0;
		int ySize = 0;

		// ask user to name ogre
		System.out.println("Please enter a name for your Ogre:");
		ogreName = scanner.nextLine();

		// ask user for swamp width
		while ((xSize <= 4) || !(xSize >= 8)) {
			System.out.println("Please enter the width of your swamp(a number between 4 - 8):");
			String xString = scanner.next();
			try {
				xSize = Integer.parseInt(xString);
				break;
			} catch (NumberFormatException nfe) {

			}
		}
		// set as width
		GameControl.setX_SIZE(xSize - 1);

		// ask user for swamp height
		while ((ySize <= 4) || !(ySize >= 8)) {
			System.out.println("Please enter the height of your swamp(a number between 4 - 8):");
			String yString = scanner.next();
			try {
				ySize = Integer.parseInt(yString);
				break;
			} catch (NumberFormatException nfe) {

			}
		}
		// set height
		GameControl.setY_SIZE(ySize - 1);

		// clear screen
		clearScreen();

		// tell player a new game is starting
		System.out.println("A new game is starting\nType help for command list:");
		// show commands
		printHelp();
		// sleep so player has time to read
		try {
			Thread.sleep(2500);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		// create new game
		GameControl gc = new GameControl(ogreName);
		// show turn
		printTurn(gc);
		// display game
		display(gc);
		// while game is not over
		while (!gc.isGameOgre()) {
			// ask user for command
			String command = scanner.next();
			System.out.println("");

			if (command.equals("move")) {
				// next turn
				gc.nextTurn();

			} else if (command.equals("undo")) {
				// undo turn
				gc.undo();
			} else if (command.equals("redo")) {
				// redo turn
				gc.redo();

			} else if (command.equals("diet")) {
				// display current diet
				System.out.print(ogreName + " says:\n'");
				gc.getCurrentState().getPlayer().getDiet().dietType();
				System.out.print("'\n");

				// display available diets
				System.out.println("Type 0 to eat Enemies!\nType 1 to eat Knights!\nType 2 to eat Very-Big-Macs!");
				int choice = scanner.nextInt();
				// if valid choice is made
				if (choice >= 0 && choice <= 2) {
					// update diet
					gc.getCurrentState().getPlayer().updateDiet(choice);
					System.out.println("\n");
					// tell player
					System.out.print(ogreName + " says:\n'");
					gc.getCurrentState().getPlayer().getDiet().dietType();
					System.out.print("'\n\n");

				} else {
					// error message if invalid input
					System.out.println("Invalid command. Pleas try again.");
				}

			} else if (command.equals("quit")) {
				// exit game loop
				break;
			} else if (command.equals("help")) {
				// display commands
				printHelp();
			} else {
				// error message/ invalid input
				System.out.println("Command not recognized. Pleas try another.");
			}
			// display turn
			printTurn(gc);
			// display board
			display(gc);
		}
		// end game
		gc.gameOver();
		System.out.println("\n");
		// sleep
		try {
			Thread.sleep(2500);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	// method to list commands available to player
	private static void printHelp() {
		System.out.println(
				"Type move to move!\nType undo to go back a turn!\nType redo to go forward a turn!\nType diet to change "
						+ ogreName + "'s Diet!\nType quit to Quit!\n");
	}

	// print the current turn
	private static void printTurn(GameControl gc) {
		System.out.println("Turn: " + gc.getTurnCount());
	}

	// print empty line to clear screen
	private static void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	// display the board
	private static void display(GameControl gc) {
		// assuming ogre is alive
		if (!gc.isGameOgre()) {
			// loop through y
			for (int y = 0; y <= GameControl.getY_SIZE(); y++) {
				// loop through x
				for (int x = 0; x <= GameControl.getX_SIZE(); x++) {
					// print location
					System.out.print("[" + x + ", " + y);

					// print O if player is inthat location
					Ogre og = gc.getCurrentState().getPlayer();
					if (og.getXCoord() == x && og.getYCoord() == y) {
						System.out.print(" O");
					}
					// loop through enemies
					for (SwampDenizen actor : gc.getCurrentState().getEnemyList()) {
						if (actor.getXCoord() == x && actor.getYCoord() == y) {
							// print first char of thier name if they are in
							// location
							System.out.print(" " + actor.getName().substring(0, 1));
						}
					}
					// close location
					System.out.print("]");
				}
				// next line
				System.out.print("\n");
			}
			// end line
			System.out.print("\n");
		}
	}

}
