package swampWars.strategy;

import java.io.Serializable;

//diet for eating burgers
@SuppressWarnings("serial")
public class MacDiet implements Diet, Serializable {

	@Override
	public void dietType() {
		// return diet
		System.out.print("I am hungry for Very-Big-Macs®!");
	}

}
