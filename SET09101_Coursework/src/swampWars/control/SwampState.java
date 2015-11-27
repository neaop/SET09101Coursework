package swampWars.control;

import java.util.ArrayList;
import swampWars.actors.Enemy;
import swampWars.actors.Ogre;

@SuppressWarnings("serial")
public class SwampState implements java.io.Serializable {

	private Ogre player;
	private ArrayList<Enemy> enemyList;

	// constructor
	public SwampState() {
		this.enemyList = new ArrayList<Enemy>();
	}

	public void addEnemy(Enemy enemy) {
		this.getEnemyList().add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		this.getEnemyList().remove(enemy);
	}

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
