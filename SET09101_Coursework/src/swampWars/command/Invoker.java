package swampWars.command;

import java.util.ArrayList;

public class Invoker {

	private ArrayList<Command> theCommands = new ArrayList<Command>();

	public void addCommand(Command theCommand) {
		this.theCommands.add(theCommand);
	}

	public void execute() {
		for (Command command : this.theCommands) {
			command.execute();
		}
	}
}
