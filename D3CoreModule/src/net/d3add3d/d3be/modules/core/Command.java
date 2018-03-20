package net.d3add3d.d3be.modules.core;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.ArrayList;

public abstract class Command {
	private String keyword;
	private ArrayList<String> aliases = new ArrayList<>();
	private String description;
	private String longDescription;
	private String format;
	protected ICommandExecutor executor;

	public Command(ICommandExecutor executor) {
		this.executor = executor;
	}

	public String getKeyword() {
		return keyword;
	}

	public Command setKeyword(String keyword) {
		this.keyword = keyword;
		return this;
	}

	public ArrayList<String> getAliases() {
		return this.aliases;
	}

	public Command setAliases(ArrayList<String> aliases) {
		this.aliases = aliases;
		return this;
	}

	public Command addAlias(String alias) {
		this.aliases.add(alias);
		return this;
	}

	public Command removeAlias(String alias) {
		this.aliases.remove(alias);
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Command setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getFormat() {
		return format;
	}

	public Command setFormat(String format) {
		this.format = format;
		return this;
	}

	public void execute(MessageReceivedEvent evnt) {
		executor.execute(evnt);
	}

	public String getLongDescription() {
		return longDescription;
	}

	public Command setLongDescription(String longDescription) {
		this.longDescription = longDescription;
		return this;
	}
}
