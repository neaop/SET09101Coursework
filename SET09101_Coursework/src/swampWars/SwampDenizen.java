package swampWars;

import java.util.Random;

import swampWars.control.SwampState;

public abstract class SwampDenizen {

	protected int _xCoord, _yCoord;

	public void move() {
		int newX = 0, newY = 0, ran = 0;
		Random rn = new Random();
		int max = SwampState.get_GRIDSIZE();

		// change x coord
		if (_xCoord == 0) {
			ran = rn.nextInt(2);
			if (ran == 1) {
				newX = _xCoord + 1;
			}
		} else if (_xCoord == max) {
			ran = rn.nextInt(2);
			if (ran == 1) {
				newX = _xCoord - 1;
			}
		} else {
			ran = rn.nextInt(3);
			if (ran == 1) {
				newX = _xCoord - 1;
			} else if (ran == 2) {
				newX = _xCoord + 1;
			}
		}

		// change y coord
		if (_yCoord == 0) {
			ran = rn.nextInt(2);
			if (ran == 1) {
				newY = _yCoord + 1;
			}
		} else if (_yCoord == max) {
			ran = rn.nextInt(2);
			if (ran == 1) {
				newY = _yCoord - 1;
			}
		} else {
			ran = rn.nextInt(3);
			if (ran == 1) {
				newY = _yCoord - 1;
			} else if (ran == 2) {
				newY = _yCoord + 1;
			}
		}

		if (newX == _xCoord && newY == _yCoord) {
			this.move();
		} else {
			System.out.println("I have moved from " + _xCoord + ", " + _yCoord + " to " + newX + ", " + newY);
			_xCoord = newX;
			_yCoord = newY;
		}
	}

	public int get_xCoord() {
		return _xCoord;
	}

	public int get_yCoord() {
		return _yCoord;
	}

	public void set_xCoord(int _xCoord) {
		this._xCoord = _xCoord;
	}

	public void set_yCoord(int _yCoord) {
		this._yCoord = _yCoord;
	}

}