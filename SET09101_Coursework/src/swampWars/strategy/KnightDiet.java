package swampWars.strategy;

import java.io.Serializable;

public class KnightDiet implements Diet, Serializable {

	@Override
	public void Diet() {
		System.out.println("I am hungry for Knights (in shining armor)!");
	}

}
