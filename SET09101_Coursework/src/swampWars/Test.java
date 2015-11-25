package swampWars;

import java.util.Random;

import swampWars.control.SwampState;

public class Test {

	public static void main(String[] args) {
		Random rn = new Random();
		SwampState.set_GRIDSIZE(5);
		EnemySpawner es = new EnemySpawner();
		Enemy e = es.spawnEnemy(rn.nextInt(3));

		for (int i = 0; i < 1000; i++)
			e.move();
	}

}
