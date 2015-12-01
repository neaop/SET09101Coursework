package swampWars.control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import swampWars.actors.Enemy;
import swampWars.actors.EnemySpawner;
import swampWars.actors.Ogre;
import swampWars.actors.SwampDenizen;
import swampWars.command.Command;
import swampWars.command.CommandGenerator;
import swampWars.command.Invoker;
import swampWars.command.MoveCommand;
import swampWars.strategy.Diet;
import swampWars.strategy.EnemyDiet;

//class to execute running of game
public class GameControl {

	// default grid size
	private static int X_SIZE = 3;
	private static int Y_SIZE = 3;
	// turn counter
	private int turnCount;
	// game over bool
	private boolean gameOgre;
	// stacks to hold previous/next moves
	private Stack<SwampState> undo, redo;
	// current state of game board
	private SwampState currentState;
	// invoker to execute move commands
	private Invoker invoker;

	// constructor
	public GameControl(String ogreName) {
		// create new ogre(name is player input)
		Ogre ogre = new Ogre(ogreName);
		// set game to turn 1
		this.turnCount = 1;
		// set game to not be over
		this.gameOgre = false;
		// define stacks
		this.undo = new Stack<SwampState>();
		this.redo = new Stack<SwampState>();
		// declare a new swamp state for game to start on
		this.setCurrentState(new SwampState());
		// set ogre as current player
		this.getCurrentState().setPlayer(ogre);
	}

	// moves all living actors
	public void nextTurn() {
		// check if the ogre is still alive
		if (!this.gameOgre) {
			// increment turn counter
			this.turnCount++;
			// empty redo stack
			this.redo.clear();

			// deep copy and push current swampState into undo stack
			SwampState swampCopy = (SwampState) SwampSerilizer.copy(this.currentState);
			this.undo.push(swampCopy);

			// clear invoker of all commands
			this.invoker = new Invoker();

			// create move command for player
			Command move = new MoveCommand(this.currentState.getPlayer());
			// add to invoker
			this.invoker.addCommand(move);

			// declare new array for enemy move commands generators
			CommandGenerator[] cg = new CommandGenerator[this.currentState.getEnemyList().size()];

			// loop through every enemy
			for (int i = 0; i < this.currentState.getEnemyList().size(); i++) {
				// declare new move command generator for current enemy
				cg[i] = new CommandGenerator(this.currentState.getEnemyList().get(i), invoker);
				// start thread to generate move command
				cg[i].start();
			}

			// loop through every command generator
			for (int i = 0; i < this.currentState.getEnemyList().size(); i++) {
				try {
					// ensure command generator is finished
					cg[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// execute all move commands
			invoker.execute();
			// potentially spawn new enemy
			this.rollEnemy();
			// check for any conflicts
			this.conflictCheck();
			System.out.println();

		} else {
		}
	}

	// spawns a new random enemy 1/3 times
	public void rollEnemy() {
		// roll random number
		Random rand = new Random();
		int rn = rand.nextInt(3);
		// if random value is 0
		if (rn == 0) {
			// create new monster
			Enemy en = EnemySpawner.randomEnemy();
			System.out.println("A " + en.getName() + " has entered the swamp!");
			// add new enemy to current state
			this.currentState.addEnemy(en);
		}
	}

	// method to go back turn
	public void undo() {
		// check if their is a move to go back to
		if (this.undo.size() >= 1) {
			// decrement turn counter
			this.turnCount--;
			// pop previous state off of stack
			SwampState prev = this.undo.pop();
			// deep copy and push current state onto redo stack
			SwampState swampCopy = (SwampState) SwampSerilizer.copy(this.currentState);
			this.redo.push(swampCopy);
			// set current state as previous state
			this.currentState = prev;
		} else {
			// error message if no moves to undo
			System.out.println("No moves to undo, you dummy!");
		}
	}

	// method to return to previous state
	public void redo() {
		// check if moves to return to
		if (this.redo.size() >= 1) {
			// increment turn counter
			this.turnCount++;
			// pop next turn off of stack
			SwampState next = this.redo.pop();
			// deep copy and push current state onto undo stack
			SwampState swampCopy = (SwampState) SwampSerilizer.copy(this.currentState);
			this.undo.push(swampCopy);
			// set next turn as current state
			this.currentState = next;
		} else {
			// error message no moves to redo
			System.out.println("No moves to redo, dumdum!");
		}
	}

	// check if any conflicts have occurred on game board
	public void conflictCheck() {
		// if there is at least one enemy
		if (this.currentState.getEnemyList().size() > 0) {
			// get current location of player
			int[] loc = this.currentState.getPlayer().getLocation();
			// declare new array list for all enemies on same square as player
			ArrayList<Enemy> baddies = new ArrayList<Enemy>();

			// loop through enemies
			for (Enemy en : this.currentState.getEnemyList()) {
				// if enemy location is same as players
				if (Arrays.equals(loc, en.getLocation())) {
					// add to arrayList
					baddies.add(en);
				}
			}
			// if at least one enemy is on same square as player
			if (baddies.size() > 0) {
				// output that a conflict has occurred
				System.out.println("A fight has broken out at " + Arrays.toString(loc) + "!");

				// if only one enemy on player
				if (baddies.size() == 1) {
					// tell player
					System.out.println(this.currentState.getPlayer().getName() + " has smooshed a "
							+ ((Enemy) baddies.get(0)).getName() + "!");
					// increment player kill count
					this.currentState.getPlayer().addSmoosh();
					// remove enemy from game
					this.currentState.removeEnemy((Enemy) baddies.get(0));

					// if two enemies in conflict
				} else if (baddies.size() == 2) {
					// get players current diet
					Diet diet = this.currentState.getPlayer().getDiet();

					// if diet isn't enemy diet
					if (!(diet instanceof EnemyDiet)) {
						// tell player they have died
						System.out.println(this.currentState.getPlayer().getName() + " has been smooshed by a "
								+ baddies.get(0).getName() + " and a " + baddies.get(1).getName() + "!\n");
						// game over
						this.gameOgre = true;

					} else {
						// tell player they have won the fight
						System.out.println(this.currentState.getPlayer().getName() + " smooshed a "
								+ baddies.get(0).getName() + " and a " + baddies.get(1).getName() + "!");
						// loop through enemies
						for (Enemy en : baddies) {
							// remove from game
							this.currentState.removeEnemy(en);
							// increment kill count
							this.currentState.getPlayer().addSmoosh();
						}
					}

					// if more that 2 enemies
				} else {
					// tell player they have died
					System.out.println(this.currentState.getPlayer().getName()
							+ " has been smooshed by many baddies!\nGame Ogre!");
					// game over
					this.gameOgre = true;
				}
			}

			// if no enemies
		} else {
			// end conflict check
			return;
		}
	}

	// game over method
	public void gameOver() {
		// flip bool to true
		if (this.gameOgre) {
			this.gameOgre = (!gameOgre);
		}
		// tell player the game is over
		System.out.println("GAME OGRE!\nYou survived " + this.turnCount + " turns and smooshed "
				+ this.currentState.getPlayer().getSmooshCounter() + " enemies!");
	}

	// getters/setters
	public Stack<SwampState> getUndo() {
		return undo;
	}

	public void setUndo(Stack<SwampState> undo) {
		this.undo = undo;
	}

	public Stack<SwampState> getRedo() {
		return redo;
	}

	public void setRedo(Stack<SwampState> redo) {
		this.redo = redo;
	}

	public SwampState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(SwampState currentState) {
		this.currentState = currentState;
	}

	public boolean isGameOgre() {
		return gameOgre;
	}

	public static int getX_SIZE() {
		return X_SIZE;
	}

	public static void setX_SIZE(int x_SIZE) {
		X_SIZE = x_SIZE;
	}

	public static int getY_SIZE() {
		return Y_SIZE;
	}

	public static void setY_SIZE(int y_SIZE) {
		Y_SIZE = y_SIZE;
	}

	public int getTurnCount() {
		return turnCount;
	}

}