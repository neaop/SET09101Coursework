package swampWars.command;

import swampWars.actors.Enemy;

/**
 * Threaded class to generate move commands for an enemy
 * 
 * @author Sam Dixon
 *
 */
public class CommandGenerator extends Thread {

	private Enemy enemy;
	private Invoker invoker;

	/**
	 * Constructor
	 * 
	 * @param enmy
	 * @param invk
	 */
	public CommandGenerator(Enemy enmy, Invoker invk) {
		this.enemy = enmy;
		this.invoker = invk;
	}

	/**
	 * Create new move command for enemy, and add to invoker (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		Command move = new MoveCommand(this.enemy);
		this.invoker.addCommand(move);
	}

}