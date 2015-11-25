package swampWars;

import java.util.ArrayList;

import swampWars.command.Command;
import swampWars.command.Invoker;
import swampWars.command.MoveCommand;
import swampWars.control.SwampState;
import swampWars.factory.Enemy;
import swampWars.factory.EnemySpawner;

public class Test {

	public static void main(String[] args) {
		SwampState.set_GRIDSIZE(3);
		Invoker invoke = new Invoker();

		EnemySpawner es = new EnemySpawner();
		Enemy e1 = es.randomEnemy();
		Enemy e2 = es.randomEnemy();

		ArrayList<Enemy> baddies = new ArrayList<Enemy>();

		baddies.add(e1);
		baddies.add(e2);
		for (Enemy en : baddies) {
			Command move = new MoveCommand(en);
			invoke.addCommand(move);
		}
		invoke.execute();

	}

}
