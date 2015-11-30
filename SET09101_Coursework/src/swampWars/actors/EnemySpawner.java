package swampWars.actors;

import java.util.Random;

//class to generate new, random enemies
public final class EnemySpawner {

	// private constructor, never instantiated
	private EnemySpawner() {
	}

	// generate a new enemy, depending on int input
	private static Enemy spawnEnemy(int type) {
		// declare new enemy
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

	// generate a random enemy
	public static Enemy randomEnemy() {
		// declare new random
		Random rand = new Random();
		// roll a int between 0-2 and return that enemy
		return EnemySpawner.spawnEnemy(rand.nextInt(3));
	}

}
