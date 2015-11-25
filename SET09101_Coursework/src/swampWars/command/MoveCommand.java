package swampWars.command;

import swampWars.SwampDenizen;

public class MoveCommand implements Command {
	private SwampDenizen _theDenizen;

	public MoveCommand(SwampDenizen denizen) {
		this._theDenizen = denizen;
	}

	@Override
	public void execute() {
		this._theDenizen.move();
	}

}
