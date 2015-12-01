package swampWars.control;

import java.util.ArrayList;
import swampWars.actors.Enemy;
import swampWars.actors.Ogre;

//class to hold the current state of the game board
@SuppressWarnings("serial")
public class SwampState implements java.io.Serializable {
	// current player
	private Ogre player;
	// list of currently alive enemies
	private ArrayList<Enemy> enemyList;

	// constructor
	public SwampState() {
		// define enemy list
		this.enemyList = new ArrayList<Enemy>();
	}

	// add enemy to current state
	public void addEnemy(Enemy enemy) {
		this.getEnemyList().add(enemy);
	}

	// remove enemy from list
	public void removeEnemy(Enemy enemy) {
		this.getEnemyList().remove(enemy);
	}

	// getters/setters
	public Ogre getPlayer() {
		return player;
	}

	public void setPlayer(Ogre p1) {
		this.player = p1;
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

}
