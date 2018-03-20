package net.d3add3d.d3be.modules.mod;

import net.d3add3d.d3be.modules.core.Command;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

public class ModCommand extends Command {
    public ModCommand(ICommandExecutor executor) {
        super(executor);
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        executor.execute(event);
    }
}
