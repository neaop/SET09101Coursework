package swampWars;

import java.util.ArrayList;
import swampWars.Ogre;
import swampWars.factory.Enemy;

public class SwampState implements java.io.Serializable {

	// private int turn;
	private Ogre player;
	private ArrayList<Enemy> enemyList;

	// constructor
	public SwampState(/* int turn */) {
		this.enemyList = new ArrayList<Enemy>();
		// this.turn = turn;
	}

	public void addEnemy(Enemy enemy) {
		this.getEnemyList().add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		this.getEnemyList().remove(enemy);
	}

	// public int getTurn() {
	// return turn;
	// }
	//
	// public void setTurn(int turn) {
	// this.turn = turn;
	// }

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
