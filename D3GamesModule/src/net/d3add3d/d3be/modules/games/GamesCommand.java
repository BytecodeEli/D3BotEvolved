package net.d3add3d.d3be.modules.games;

import net.d3add3d.d3be.modules.core.Command;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;


public class GamesCommand extends Command {

	public GamesCommand(ICommandExecutor executor) {
		super(executor);
	}

	@Override
	public void execute(MessageReceivedEvent event) {
		executor.execute(event);
	}

}
