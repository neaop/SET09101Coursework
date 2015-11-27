package swampWars.strategy;

import java.io.Serializable;

public class MacDiet implements Diet, Serializable {

	@Override
	public void Diet() {
		System.out.println("I am hungry for Very-Big-Macs®!");
	}

}
