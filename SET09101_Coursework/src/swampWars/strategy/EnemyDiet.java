package swampWars.strategy;

import java.io.Serializable;

public class EnemyDiet implements Diet, Serializable {

	@Override
	public void Diet() {
		System.out.println("I am hungry for Ogre Enemies!");
	}

}
