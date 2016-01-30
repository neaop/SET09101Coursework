package swampWars.command;

import swampWars.actors.SwampDenizen;

//move command to execute an actors move method
public class MoveCommand implements Command {
	private SwampDenizen theDenizen;

	// constructor
	public MoveCommand(SwampDenizen denizen) {
		this.theDenizen = denizen;
	}

	// move actor
	@Override
	public void execute() {
		this.theDenizen.move();
	}

}
