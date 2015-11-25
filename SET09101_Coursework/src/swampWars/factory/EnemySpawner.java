package swampWars.factory;

import java.util.Random;

public class EnemySpawner {

	private Random rand;

	public EnemySpawner() {
		rand = new Random();
	}

	public Enemy spawnEnemy(int type) {
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

	public Enemy randomEnemy() {
		return this.spawnEnemy(rand.nextInt(2));
	}

}
