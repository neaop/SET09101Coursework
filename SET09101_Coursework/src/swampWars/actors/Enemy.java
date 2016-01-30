package swampWars.actors;

//base class for all enemies
@SuppressWarnings("serial")
public abstract class Enemy extends SwampDenizen {

	// constructor
	public Enemy() {
		// starting locations for all enemies
		setXCoord(0);
		setYCoord(0);
	}

}
