package swampWars;

import java.util.Random;
import java.util.Stack;

import swampWars.command.Command;
import swampWars.command.Invoker;
import swampWars.command.MoveCommand;
import swampWars.factory.Enemy;
import swampWars.factory.EnemySpawner;

public class GameControl {

	private static int GRIDSIZE = 3;
	private Stack<SwampState> undo, redo;
	private SwampState currentState;
	private int turnCount;

	private Invoker invoker;

	public GameControl(String name) {
		this.undo = new Stack<SwampState>();
		this.redo = new Stack<SwampState>();
		Ogre ogre = new Ogre(name);
		this.turnCount = 1;
		this.setCurrentState(new SwampState());
		this.getCurrentState().setPlayer(ogre);
	}

	public void nextTurn() {
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
			System.out.println("A " + en.getName() + " has enterd the swamp!");
			this.currentState.addEnemy(en);
		}
		System.out.println("");
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

	public void display() {
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
