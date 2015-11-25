package swampWars.factory;

import swampWars.SwampDenizen;

public class Enemy extends SwampDenizen {

	private static int _ID_INDEX;
	private String _name;
	private int _id;

	public Enemy() {
		_id = _ID_INDEX;
		_ID_INDEX += 1;
		set_xCoord(0);
		set_yCoord(0);
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public int get_id() {
		return _id;
	}
}
