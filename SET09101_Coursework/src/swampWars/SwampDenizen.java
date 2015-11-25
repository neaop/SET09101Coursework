package swampWars;

public abstract class SwampDenizen {

	protected int _xCoord, _yCoord;

	public void move() {
		System.out.println("I have moved!\nx = " + _xCoord + "\ny = " + _yCoord);
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