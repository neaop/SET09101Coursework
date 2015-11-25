package swampWars.control;

import java.util.ArrayList;
import swampWars.Enemy;
import swampWars.Player;

public class SwampState {
	private static int _GRIDSIZE;
	private Player _p1;
	private ArrayList<Enemy> _enemyList;

	// constructor
	public SwampState(int size) {
		set_GRIDSIZE(size);
	}

	public static int get_GRIDSIZE() {
		return _GRIDSIZE;
	}

	public Player get_p1() {
		return _p1;
	}

	public ArrayList<Enemy> get_enemyList() {
		return _enemyList;
	}

	public static void set_GRIDSIZE(int _GRIDSIZE) {
		SwampState._GRIDSIZE = _GRIDSIZE;
	}

	public void set_p1(Player _p1) {
		this._p1 = _p1;
	}

	public void set_enemyList(ArrayList<Enemy> _enemyList) {
		this._enemyList = _enemyList;
	}

}
