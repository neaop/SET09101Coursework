package swampWars.strategy;

import java.io.Serializable;

// diet for eating enemies
@SuppressWarnings("serial")
public class EnemyDiet implements Diet, Serializable {

	@Override
	public void dietType() {
		// return diet
		System.out.print("I am hungry for Ogre Enemies!");
	}

}
