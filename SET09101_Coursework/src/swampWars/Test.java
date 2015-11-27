package swampWars;

import swampWars.actors.Ogre;
import swampWars.control.GameControl;

public class Test {

	public static void main(String[] args) {
		GameControl gc = new GameControl("drek");
		gc.display();
		for (int i = 0; i < 9; i++) {
			gc.nextTurn();
			gc.display();
		}

	}

}
