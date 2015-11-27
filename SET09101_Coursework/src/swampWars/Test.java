package swampWars;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import swampWars.actors.Enemy;
import swampWars.actors.EnemySpawner;
import swampWars.actors.Ogre;
import swampWars.control.GameControl;

public class Test {

	public static void main(String[] args) {
		GameControl gc = new GameControl("Drek");
		gc.display();

		for (int i = 0; i < 99; i++) {
			gc.nextTurn();
			gc.display();
		}
	}

}
