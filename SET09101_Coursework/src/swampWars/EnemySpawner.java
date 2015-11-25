package swampWars;

public class EnemySpawner {

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

}
