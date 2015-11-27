package swampWars;

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
