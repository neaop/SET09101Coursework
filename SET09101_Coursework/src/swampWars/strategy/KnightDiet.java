package swampWars.strategy;

import java.io.Serializable;

//diet for eating knights
@SuppressWarnings("serial")
public class KnightDiet implements Diet, Serializable {

	@Override
	public void dietType() {
		// return diet
		System.out.print("I am hungry for Knights (in shining armor)!");
	}

}
