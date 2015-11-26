package swampWars;

import java.util.ArrayList;
import swampWars.Ogre;
import swampWars.factory.Enemy;

public class SwampState implements java.io.Serializable {

	private static int GRIDSIZE;
	private int turn;
	private Ogre p1;
	private ArrayList<Enemy> enemyList;

	// constructor
	public SwampState(int size) {
		this.enemyList = new ArrayList<Enemy>();
		setGRIDSIZE(size);
	}

	public void addEnemy(Enemy enemy) {
		this.getEnemyList().add(enemy);
	}

	public void removeEnemy(Enemy enemy) {
		this.getEnemyList().remove(enemy);
	}

	public static int getGRIDSIZE() {
		return GRIDSIZE;
	}

	public static void setGRIDSIZE(int gRIDSIZE) {
		GRIDSIZE = gRIDSIZE;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Ogre getP1() {
		return p1;
	}

	public void setP1(Ogre p1) {
		this.p1 = p1;
	}

	public ArrayList<Enemy> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<Enemy> enemyList) {
		this.enemyList = enemyList;
	}

}
