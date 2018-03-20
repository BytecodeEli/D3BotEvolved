package net.d3add3d.d3be.modules.core;

import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IMessage;

public interface ICommandExecutor {

	boolean execute(MessageReceivedEvent event);

}
