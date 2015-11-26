package swampWars;

import java.util.ArrayList;
import swampWars.command.Command;
import swampWars.command.Invoker;
import swampWars.command.MoveCommand;
import swampWars.factory.Donkey;
import swampWars.factory.Enemy;
import swampWars.factory.EnemySpawner;
import swampWars.factory.Parrot;

public class Test {

	public static void main(String[] args) {
		GameControl.setGRIDSIZE(3);
		GameControl gc = new GameControl("Drek");
		gc.display();
		for (int i = 0; i < 10; i++) {
			gc.nextTurn();
			gc.display();

		}

		// Enemy e1 = new Donkey();
		// Enemy e2 = new Parrot();
		//
		// SwampState swamp1 = new SwampState();
		// swamp1.addEnemy(e1);
		// SwampState swamp2 = (SwampState) SwampSerilizer.copy(swamp1);
		// swamp1.addEnemy(e2);
		// swamp1.getEnemyList().get(0).move();
		//
		// System.out.println(
		// "swamp1: " + swamp1.getEnemyList().get(0).getxCoord() +
		// swamp1.getEnemyList().get(0).getyCoord());
		// System.out.println(
		// "swamp2: " + swamp2.getEnemyList().get(0).getxCoord() +
		// swamp2.getEnemyList().get(0).getyCoord());

		// for (int i = 0; i < 1000; i++) {
		// System.out.println(EnemySpawner.randomEnemy().getName());
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
		// for (int i = 0; i < 1000; i++)
		// invoke.execute();

	}

}
