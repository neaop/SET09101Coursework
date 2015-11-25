package swampWars;

public class Test {

	public static void main(String[] args) {
		EnemySpawner es = new EnemySpawner();

		Enemy en = es.spawnEnemy(0);
		System.out.println(en.getClass());
		en.move();

		en = es.spawnEnemy(1);
		System.out.println(en.getClass());
		en.move();

		en = es.spawnEnemy(2);
		System.out.println(en.getClass());
		en.move();

		Player p1 = new Player();
		p1.get_diet().diet();

		p1.updateDiet(1);
		p1.get_diet().diet();

		p1.updateDiet(2);
		p1.get_diet().diet();
	}

}
