package swampWars;

import swampWars.diet.EnemyDiet;
import swampWars.diet.IDiet;
import swampWars.diet.KnightDiet;
import swampWars.diet.MacDiet;

public class Player extends SwampDenizen {

	private IDiet _diet;

	public Player() {
		set_diet(new EnemyDiet());
		set_xCoord(1);
		set_yCoord(1);
	}

	public void updateDiet(int type) {
		if (type == 0) {
			set_diet(new EnemyDiet());
		} else if (type == 1) {
			set_diet(new KnightDiet());
		} else if (type == 2) {
			set_diet(new MacDiet());
		}
	}

	public IDiet get_diet() {
		return _diet;
	}

	public void set_diet(IDiet _diet) {
		this._diet = _diet;
	}

}
