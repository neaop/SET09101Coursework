package swampWars;

import swampWars.factory.EnemySpawner;

public class Test {

	public static void main(String[] args) {
		SwampState.set_GRIDSIZE(3);

		for (int i = 0; i < 1000; i++) {
			System.out.println(EnemySpawner.randomEnemy().get_name());
		}

		// Invoker invoke = new Invoker();
		// Enemy e1 = es.randomEnemy();
		// Enemy e2 = es.randomEnemy();
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
