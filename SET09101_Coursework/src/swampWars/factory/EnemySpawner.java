package swampWars.factory;

import java.util.Random;

public final class EnemySpawner {

	private EnemySpawner() {
	}

	private static Enemy spawnEnemy(int type) {
		Enemy enemy = null;
		if (type == 0) {
			enemy = new Donkey();
		} else if (type == 1) {
			enemy = new Parrot();
		} else if (type == 2) {
			enemy = new Snake();
		}
		return enemy;
	}

	public static Enemy randomEnemy() {
		Random rand = new Random();
		return EnemySpawner.spawnEnemy(rand.nextInt(3));
	}

}
