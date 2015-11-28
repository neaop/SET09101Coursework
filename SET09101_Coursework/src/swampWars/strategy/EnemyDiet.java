package swampWars.strategy;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EnemyDiet implements Diet, Serializable {

	@Override
	public void Diet() {
		System.out.print("I am hungry for Ogre Enemies!");
	}

}
