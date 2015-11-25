package swampWars.command;

import java.util.ArrayList;

public class Invoker {

	private ArrayList<Command> _theCommands = new ArrayList<Command>();

	public void addCommand(Command theCommand) {
		this._theCommands.add(theCommand);
	}

	public void execute() {
		for (Command command : this._theCommands) {
			command.execute();
		}
	}
}
