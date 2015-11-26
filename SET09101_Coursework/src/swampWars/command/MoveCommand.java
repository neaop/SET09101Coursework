package swampWars.command;

import swampWars.SwampDenizen;

public class MoveCommand implements Command {
	private SwampDenizen theDenizen;

	public MoveCommand(SwampDenizen denizen) {
		this.theDenizen = denizen;
	}

	@Override
	public void execute() {
		this.theDenizen.move();
	}

}
