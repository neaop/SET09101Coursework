package swampWars.control;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import swampWars.actors.Enemy;
import swampWars.actors.EnemySpawner;
import swampWars.actors.Ogre;
import swampWars.actors.SwampDenizen;
import swampWars.command.Command;
import swampWars.command.Invoker;
import swampWars.command.MoveCommand;
import swampWars.strategy.Diet;
import swampWars.strategy.EnemyDiet;

public class GameControl {

	private static int GRIDSIZE = 3;
	private Stack<SwampState> undo, redo;
	private SwampState currentState;
	private int turnCount;
	private boolean gameOgre;

	private Invoker invoker;

	public GameControl(String name) {
		this.gameOgre = false;
		this.undo = new Stack<SwampState>();
		this.redo = new Stack<SwampState>();
		Ogre ogre = new Ogre(name);
		this.turnCount = 1;
		this.setCurrentState(new SwampState());
		this.getCurrentState().setPlayer(ogre);
	}

	public void nextTurn() {
		if (!this.gameOgre) {
			// empty redo stack
			this.redo.clear();
			// push current swampState into undo stack
			SwampState swampCopy = (SwampState) SwampSerilizer.copy(this.currentState);
			this.undo.push(swampCopy);
			// clear invoker of all commands
			this.invoker = new Invoker();
			// increment counter
			this.turnCount++;

			// create move command for player
			Command move = new MoveCommand(this.currentState.getPlayer());
			// add to invoker
			this.invoker.addCommand(move);
			// loop through every enemy in play
			for (Enemy enemy : this.currentState.getEnemyList()) {
				// create move command
				move = new MoveCommand(enemy);
				// add to invoker
				invoker.addCommand(move);
			}
			// execute all move commands
			invoker.execute();
			// roll random number
			Random rand = new Random();
			int rn = rand.nextInt(3);
			// if random value is 0
			if (rn == 0) {
				// create new monster
				Enemy en = EnemySpawner.randomEnemy();
				System.out.println("A " + en.getName() + " has entered the swamp!");
				this.currentState.addEnemy(en);
			}
			this.conflictCheck();
			System.out.println("");
		}
	}

	public void undo() {
		if (this.undo.size() >= 1) {
			this.turnCount--;
			SwampState prev = this.undo.pop();
			SwampState swampCopy = (SwampState) SwampSerilizer.copy(this.currentState);
			this.redo.push(swampCopy);
			this.currentState = prev;
		} else {
			System.out.println("No moves to undo, you dummy!");
		}
	}

	public void redo() {
		if (this.redo.size() >= 1) {
			this.turnCount++;
			SwampState next = this.redo.pop();
			SwampState swampCopy = (SwampState) SwampSerilizer.copy(this.currentState);
			this.undo.push(swampCopy);
			this.currentState = next;
		} else {
			System.out.println("No moves to redo, dumdum!");
		}
	}

	public void conflictCheck() {
		// if there is atleast one enemy
		if (this.currentState.getEnemyList().size() > 0) {
			int[] loc = this.currentState.getPlayer().getLocation();
			ArrayList<Enemy> baddies = new ArrayList<Enemy>();
			// loop through enemys
			for (Enemy en : this.currentState.getEnemyList()) {
				// if enemy location i same as players
				if (Arrays.equals(loc, en.getLocation())) {
					// add to arrayList
					baddies.add(en);
				}
			}

			if (baddies.size() > 0) {
				System.out.println("A fight has broken out at " + Arrays.toString(loc) + "!");
				if (baddies.size() == 1) {
					System.out.println(this.currentState.getPlayer().getName() + " has smooshed a "
							+ ((Enemy) baddies.get(0)).getName() + "!");
					this.currentState.removeEnemy((Enemy) baddies.get(0));

				} else if (baddies.size() == 2) {
					Diet diet = this.currentState.getPlayer().getDiet();
					if (!(diet instanceof EnemyDiet)) {
						System.out.println(this.currentState.getPlayer().getName() + " has been smooshed by a "
								+ baddies.get(0).getName() + " and a " + baddies.get(1).getName() + "!\nGame Ogre!");
						this.gameOgre = true;

					} else {
						System.out.println(this.currentState.getPlayer().getName() + " smooshed a "
								+ baddies.get(0).getName() + " and a " + baddies.get(1).getName() + "!");
						for (Enemy en : baddies) {
							this.currentState.removeEnemy(en);
						}
					}
				} else {
					System.out.println(this.currentState.getPlayer().getName()
							+ " has been smooshed by many baddies!\nGame Ogre!");
					this.gameOgre = true;

				}
			}
		} else {
			return;
		}
	}

	public void display() {
		if (!this.gameOgre) {
			System.out.println("Turn: " + this.turnCount);
			for (int i = 0; i <= GameControl.GRIDSIZE; i++) {
				for (int j = 0; j <= GameControl.GRIDSIZE; j++) {
					System.out.print("[" + i + "," + j);
					Ogre og = this.currentState.getPlayer();
					if (og.getXCoord() == i && og.getYCoord() == j) {
						System.out.print(" O");
					}
					for (SwampDenizen actor : currentState.getEnemyList()) {
						if (actor.getXCoord() == i && actor.getYCoord() == j) {
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

	public static int getGRIDSIZE() {
		return GRIDSIZE;
	}

	public static void setGRIDSIZE(int gridSize) {
		GRIDSIZE = gridSize;
	}

}
