package net.d3add3d.d3be.modules.mod;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.modules.IModule;

public class Warn implements ICommandExecutor {

    private CoreModule coreModule;
    private IModule owner;

    Warn(CoreModule core, IModule owner) {
        this.coreModule = core;
        this.owner = owner;
    }

    @Override
    public boolean execute(MessageReceivedEvent event) {
        coreModule.getUtils().sendMessage(event.getChannel(), "Not yet implemented in D3Bot Evolved, use D3bot instead.");

        return true;
    }
}
