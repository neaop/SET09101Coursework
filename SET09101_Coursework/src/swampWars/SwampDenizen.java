package swampWars;

import java.util.Random;

/**
 * A base class for all actors in the game
 * 
 * @author Sam Dixon
 *
 */
public abstract class SwampDenizen implements java.io.Serializable {

	private int xCoord, yCoord;
	private String name;

	/**
	 * Updates the actors coords to a new, random, adjecent tile
	 */
	public void move() {
		boolean boolX = false, boolY = false;
		// roll random adjacent values for each coord
		int currentX = this.xCoord;
		int currentY = this.yCoord;

		int newX = this.changeCoord("X");
		int newY = this.changeCoord("Y");

		// if values are not different from current

		if ((newX == currentX && newY == currentY)) {
			this.move();
		} else {
			// update coords to new values
			System.out.println(
					this.getName() + " has moved from " + xCoord + ", " + yCoord + " to " + newX + ", " + newY);
			this.xCoord = newX;
			yCoord = newY;
		}

	}

	/**
	 * Returns a valid int to update a coord
	 * 
	 * @param coord
	 *            The name of the coord to be updated
	 * @return A new valid coord int
	 */
	private int changeCoord(String coord) {
		// declare variables
		int currentCoord = 0, newCoord = 0, rand = 0;
		Random rn = new Random();
		// get max grid size
		int max = GameControl.getGRIDSIZE();

		// switch to select either X or Y coord
		switch (coord) {
		case "X":
			currentCoord = this.xCoord;
			break;
		case "Y":
			currentCoord = this.yCoord;
			break;
		}
		newCoord = currentCoord;

		// if coord is at min edge
		if (currentCoord == 0) {
			// roll number
			rand = rn.nextInt(2);
			if (rand == 0) {

			} else if (rand == 1) {
				// move along one square
				newCoord++;
			}

			// if coord is at max edge
		} else if (currentCoord == max) {
			// roll number
			rand = rn.nextInt(2);
			if (rand == 0) {

			} else if (rand == 1) {
				// move back one square
				newCoord--;
			}

			// if coord is not at either edge

		} else if ((currentCoord > 0) && (currentCoord < max)) {
			rand = rn.nextInt(3);
			if (rand == 0) {

			} else if (rand == 1) {
				newCoord--;
			} else if (rand == 2) {
				newCoord++;
			}
		}
		return newCoord;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}