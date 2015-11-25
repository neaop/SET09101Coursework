package swampWars;

import java.util.Random;

import swampWars.strategy.Diet;
import swampWars.strategy.EnemyDiet;
import swampWars.strategy.KnightDiet;
import swampWars.strategy.MacDiet;

public class Ogre extends SwampDenizen {

	private String _name;
	private Diet _diet;

	public Ogre(String name) {
		Random rand = new Random();

		this.set_name(name);
		this.set_xCoord(rand.nextInt(SwampState.get_GRIDSIZE()) + 1);
		this.set_yCoord(rand.nextInt(SwampState.get_GRIDSIZE()) + 1);
		updateDiet(rand.nextInt(3));

		System.out.println(this.get_name() + " is at " + this.get_xCoord() + ", " + this.get_yCoord() + ".");
	}

	public void updateDiet(int type) {
		if (type == 0) {
			this.set_diet(new EnemyDiet());
		} else if (type == 1) {
			this.set_diet(new KnightDiet());
		} else if (type == 2) {
			this.set_diet(new MacDiet());
		}
	}

	public String get_name() {
		return _name;
	}

	public Diet get_diet() {
		return _diet;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public void set_diet(Diet _diet) {
		this._diet = _diet;
	}

}