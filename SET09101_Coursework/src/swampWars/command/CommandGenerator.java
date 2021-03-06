package swampWars.command;

import swampWars.actors.Enemy;

// threaded class to generate move commands for an enemy
public class CommandGenerator extends Thread {

	private Enemy enemy;
	private Invoker invoker;

	// constructor
	public CommandGenerator(Enemy enmy, Invoker invk) {
		this.enemy = enmy;
		this.invoker = invk;
	}

	// Create new move command for enemy, and add to invoker
	@Override
	public void run() {
		Command move = new MoveCommand(this.enemy);
		this.invoker.addCommand(move);
	}

}