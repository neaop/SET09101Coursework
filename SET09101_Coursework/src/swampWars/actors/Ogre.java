package swampWars.actors;

import java.util.Random;

import swampWars.control.GameControl;
import swampWars.strategy.Diet;
import swampWars.strategy.EnemyDiet;
import swampWars.strategy.KnightDiet;
import swampWars.strategy.MacDiet;

// class for player character
@SuppressWarnings("serial")
public class Ogre extends SwampDenizen {

	private Diet diet;
	private int smooshCounter;

	// constructor
	public Ogre(String name) {
		// set number of enemies killed to 0
		this.smooshCounter = 0;

		// new random
		Random rand = new Random();
		int startX, startY;
		// roll stariting locations
		startX = rand.nextInt(GameControl.getX_SIZE() + 1);
		startY = rand.nextInt(GameControl.getY_SIZE() + 1);

		// check that start is top left
		if (startX == 0 && startY == 0) {
			// roll random
			int select = rand.nextInt(0);
			if (select == 0) {
				// increase x by one
				startX++;
			}
			if (select == 1) {
				// increase y by one
				startY++;
			}
		}
		// define start location
		this.setXCoord(startX);
		this.setYCoord(startY);

		// define name
		this.setName(name);

		// set starting diet
		updateDiet(0);

		System.out.println(this.getName() + " is at " + this.getXCoord() + ", " + this.getYCoord() + ".");
	}

	// change the ogre's current diet
	public void updateDiet(int type) {
		if (type == 0) {
			this.setDiet(new KnightDiet());
		} else if (type == 1) {
			this.setDiet(new EnemyDiet());
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

	public int getSmooshCounter() {
		return smooshCounter;
	}

	public void addSmoosh() {
		this.smooshCounter++;
	}

}