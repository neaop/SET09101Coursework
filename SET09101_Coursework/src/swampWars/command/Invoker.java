package swampWars.command;

import java.util.ArrayList;

// Invoker class to hold and run actor move commands
public class Invoker {

	private ArrayList<Command> theCommands = new ArrayList<Command>();

	// add command to list of commands
	public void addCommand(Command theCommand) {
		this.theCommands.add(theCommand);
	}

	// execute all commands in the list
	public void execute() {
		for (Command command : this.theCommands) {
			command.execute();
		}
	}

}