package swampWars.command;

import java.util.ArrayList;

/**
 * Invoker class to hold and run actor move commands
 * 
 * @author Sam Dixon
 *
 */
public class Invoker {

	private ArrayList<Command> theCommands = new ArrayList<Command>();

	/**
	 * Add a command to the list of commands
	 * 
	 * @param theCommand
	 *            command to be added to list
	 */
	public void addCommand(Command theCommand) {
		this.theCommands.add(theCommand);
	}

	/**
	 * Execute all commands in list
	 * 
	 */
	public void execute() {
		for (Command command : this.theCommands) {
			command.execute();
		}
	}

}