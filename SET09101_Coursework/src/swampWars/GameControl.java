package swampWars;

import java.util.Random;
import java.util.Stack;

import swampWars.command.Command;
import swampWars.command.Invoker;
import swampWars.command.MoveCommand;
import swampWars.factory.Enemy;
import swampWars.factory.EnemySpawner;

public class GameControl {

	private Stack<SwampState> undo, redo;
	private SwampState currentState;
	private int turnCount;
	private static int GRIDSIZE;
	private Invoker invoker;

	private GameControl() {
	}

	public GameControl(String name) {
		Ogre ogre = new Ogre(name);
		this.turnCount = 0;
		this.setCurrentState(new SwampState(/* this.turnCount */));
		this.getCurrentState().setPlayer(ogre);
	}

	public void nextTurn() {
		// push current swampState into undo stack
		// SwampState swamp2 = (SwampState)
		// SwampSerilizer.copy(this.currentState);
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
		System.out.println("\n");
	}

	public void display() {
		for (int i = 0; i <= this.GRIDSIZE; i++) {
			for (int j = 0; j <= this.GRIDSIZE; j++) {
				System.out.print("[" + i + "," + j);
				for (SwampDenizen actor : currentState.getEnemyList()) {
					if (actor.getxCoord() == i && actor.getyCoord() == j) {
						System.out.print(" " + actor.getName());
					}
				}
				System.out.print("]");
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

	public static void setGRIDSIZE(int gRIDSIZE) {
		GRIDSIZE = gRIDSIZE;
	}

}
