package swampWars;

import java.util.Random;

import swampWars.strategy.Diet;
import swampWars.strategy.EnemyDiet;
import swampWars.strategy.KnightDiet;
import swampWars.strategy.MacDiet;

public class Ogre extends SwampDenizen {

	private Diet diet;

	public Ogre(String name) {
		Random rand = new Random();
		int startX, startY;
		startX = rand.nextInt(GameControl.getGRIDSIZE() + 1);
		startY = rand.nextInt(GameControl.getGRIDSIZE() + 1);

		if (startX == 0 && startY == 0) {
			int select = rand.nextInt(2);
			if (select == 0) {
				startX++;
			}
			if (select == 1) {
				startY++;
			}
		}
		this.setxCoord(startX);
		this.setyCoord(startY);

		this.setName(name);

		updateDiet(rand.nextInt(3));

		System.out.println(this.getName() + " is at " + this.getxCoord() + ", " + this.getyCoord() + ".");
	}

	public void updateDiet(int type) {
		if (type == 0) {
			this.setDiet(new EnemyDiet());
		} else if (type == 1) {
			this.setDiet(new KnightDiet());
		} else if (type == 2) {
			this.setDiet(new MacDiet());
		}
	}

	public Diet getDiet() {
		return diet;
	}

	public void setDiet(Diet diet) {
		this.diet = diet;
	}

}