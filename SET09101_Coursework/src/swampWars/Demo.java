package swampWars;

import swampWars.control.GameControl;

public class Demo {

	public static void main(String[] args) {
		GameControl gc = new GameControl("Drek");
		gc.getCurrentState().getPlayer().updateDiet(2);
		while (!gc.isGameOgre()) {
			gc.nextTurn();
			gc.display();
		}
		gc.gameOver();
	}

}
