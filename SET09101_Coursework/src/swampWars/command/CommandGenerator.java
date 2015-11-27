package swampWars.command;

import swampWars.actors.Enemy;

public class CommandGenerator extends Thread {

	private Enemy enemy;
	private Invoker invoker;

	public CommandGenerator(Enemy eme, Invoker invk) {
		this.enemy = eme;
		this.invoker = invk;
	}

	@Override
	public void run() {
		Command move = new MoveCommand(this.enemy);
		this.invoker.addCommand(move);
	}

}
