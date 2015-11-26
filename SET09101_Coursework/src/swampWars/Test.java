package swampWars;

import java.util.ArrayList;

import swampWars.command.Command;
import swampWars.command.Invoker;
import swampWars.command.MoveCommand;
import swampWars.factory.Donkey;
import swampWars.factory.Enemy;
import swampWars.factory.Parrot;

public class Test {

	public static void main(String[] args) {
		SwampState.set_GRIDSIZE(3);

		Enemy e1 = new Donkey();
		Enemy e2 = new Parrot();

		SwampState swamp1 = new SwampState(3);
		swamp1.addEnemy(e1);
		SwampState swamp2 = (SwampState) SwampSerilizer.copy(swamp1);
		swamp1.addEnemy(e2);

		System.out.println(swamp1.get_enemyList());
		System.out.println(swamp2.get_enemyList());

		// for (int i = 0; i < 1000; i++) {
		// System.out.println(EnemySpawner.randomEnemy().get_name());
		// }

		// Invoker invoke = new Invoker();
		// Enemy e1 = EnemySpawner.randomEnemy();
		// Enemy e2 = EnemySpawner.randomEnemy();
		//
		// ArrayList<Enemy> baddies = new ArrayList<Enemy>();
		//
		// baddies.add(e1);
		// baddies.add(e2);
		// for (Enemy en : baddies) {
		// Command move = new MoveCommand(en);
		// invoke.addCommand(move);
		// }
		// invoke.execute();

	}

}
