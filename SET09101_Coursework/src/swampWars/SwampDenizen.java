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
		// roll random adjecent values for each coord
		int newX = this.changeCoord("X");
		int newY = this.changeCoord("Y");

		// if values are not diffrent from current
		if (newX == xCoord && newY == yCoord) {
			// roll again
			this.move();
		} else {
			// update coords to new values
			System.out.println(
					this.getName() + " has moved from " + xCoord + ", " + yCoord + " to " + newX + ", " + newY);
			xCoord = newX;
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
		// declare varibles
		int currentCoord = 0, newCoord = 0, rand = 0;
		Random rn = new Random();
		// get max grid size
		int max = SwampState.getGRIDSIZE();

		// switch to select either X or Y coord
		switch (coord) {
		case "X":
			currentCoord = this.xCoord;
			break;
		case "Y":
			currentCoord = this.yCoord;
			break;
		}

		// if coord is at min edge
		if (currentCoord == 0) {
			// roll number
			rand = rn.nextInt(2);
			if (rand == 1) {
				// move down one square
				newCoord = currentCoord + 1;
			}
			// if coord is at max edge
		} else if (currentCoord == max) {
			// roll number
			rand = rn.nextInt(2);
			if (rand == 1) {
				// move up one square
				newCoord = currentCoord - 1;
			}
			// if coord is not at either edge
		} else {
			rand = rn.nextInt(3);
			if (rand == 1) {
				newCoord = currentCoord - 1;
			} else if (rand == 2) {
				newCoord = currentCoord + 1;
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