package net.d3add3d.d3be.modules.core;

import java.util.ArrayList;

public class ModuleObj {

	private ArrayList<Command> commands = new ArrayList<>();
	private boolean isAvailable = false;
	private String name;


	public ModuleObj(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

	public void setCommands(ArrayList<Command> commands) {
		this.commands = commands;
	}

	public void addCommand(Command command) {
		this.commands.add(command);
	}

	public void removeCommand(Command command) {
		this.commands.remove(command);
	}

	public String getName() {
		return name;
	}
}
