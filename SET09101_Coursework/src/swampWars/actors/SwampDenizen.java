package swampWars.actors;

import java.util.Random;

import swampWars.control.GameControl;

//base class for all actors in game
@SuppressWarnings("serial")
public abstract class SwampDenizen implements java.io.Serializable {

	private int xCoord, yCoord;
	private String name;

	// method to change current coordinates to a new,adjecent ,random location
	public void move() {
		// define variables
		Random rn = new Random();
		int newX = this.xCoord;
		int newY = this.yCoord;
		int rand = 0;

		// check if position hasn't changed or has moved off of grid
		while ((newX == this.xCoord && newY == this.yCoord)
				|| (newX > GameControl.getX_SIZE() || newY > GameControl.getY_SIZE()) || (newX < 0 || newY < 0)) {
			// reset new position to be current position
			newX = this.xCoord;
			newY = this.yCoord;
			// Generate number between -1 - 1
			rand = rn.nextInt(3) - 1;
			// add to current position
			newX += rand;
			rand = rn.nextInt(3) - 1;
			newY += rand;
		}
		// print old coords and new coords
		System.out.println(this.getName() + " has moved from " + xCoord + ", " + yCoord + " to " + newX + ", " + newY);

		// update coords to new
		this.xCoord = newX;
		this.yCoord = newY;
	}

	// returns an array containing the x,y location of an actor
	public int[] getLocation() {
		int[] location = new int[] { this.getXCoord(), this.getYCoord() };
		return location;
	}

	// getters/setters
	public int getXCoord() {
		return xCoord;
	}

	public void setXCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getYCoord() {
		return yCoord;
	}

	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}