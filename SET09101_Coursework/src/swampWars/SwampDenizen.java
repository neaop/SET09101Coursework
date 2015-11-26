package swampWars;

import java.util.Random;

/**
 * A base class for all actors in the game
 * 
 * @author Sam Dixon
 *
 */
public abstract class SwampDenizen implements java.io.Serializable {

	private int _xCoord, _yCoord;
	private String _name;

	/**
	 * Updates the actors coords to a new, random, adjecent tile
	 */
	public void move() {
		// roll random adjecent values for each coord
		int newX = this.changeCoord("X");
		int newY = this.changeCoord("Y");

		// if values are not diffrent from current
		if (newX == _xCoord && newY == _yCoord) {
			// roll again
			this.move();
		} else {
			// update coords to new values
			System.out.println(
					this.get_name() + " has moved from " + _xCoord + ", " + _yCoord + " to " + newX + ", " + newY);
			_xCoord = newX;
			_yCoord = newY;
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
		int max = SwampState.get_GRIDSIZE();

		// switch to select either X or Y coord
		switch (coord) {
		case "X":
			currentCoord = this._xCoord;
			break;
		case "Y":
			currentCoord = this._yCoord;
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

	public int get_xCoord() {
		return _xCoord;
	}

	public int get_yCoord() {
		return _yCoord;
	}

	public String get_name() {
		return _name;
	}

	public void set_xCoord(int _xCoord) {
		this._xCoord = _xCoord;
	}

	public void set_yCoord(int _yCoord) {
		this._yCoord = _yCoord;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

}