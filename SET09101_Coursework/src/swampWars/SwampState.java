package swampWars;

import java.util.ArrayList;
import swampWars.Ogre;
import swampWars.factory.Enemy;

public class SwampState implements java.io.Serializable {
	private static int _GRIDSIZE;
	private Ogre _p1;
	private ArrayList<Enemy> _enemyList;

	// constructor
	public SwampState(int size) {
		this._enemyList = new ArrayList<Enemy>();
		set_GRIDSIZE(size);
	}

	public void addEnemy(Enemy enemy) {
		this.get_enemyList().add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		this.get_enemyList().remove(enemy);
	}

	public static int get_GRIDSIZE() {
		return _GRIDSIZE;
	}

	public Ogre get_p1() {
		return _p1;
	}

	public ArrayList<Enemy> get_enemyList() {
		return _enemyList;
	}

	public static void set_GRIDSIZE(int _GRIDSIZE) {
		SwampState._GRIDSIZE = _GRIDSIZE;
	}

	public void set_p1(Ogre _p1) {
		this._p1 = _p1;
	}

	public void set_enemyList(ArrayList<Enemy> _enemyList) {
		this._enemyList = _enemyList;
	}

}
