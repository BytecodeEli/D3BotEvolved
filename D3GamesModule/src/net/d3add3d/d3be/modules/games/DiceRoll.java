package net.d3add3d.d3be.modules.games;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.modules.IModule;

import java.util.Random;

public class DiceRoll implements ICommandExecutor {

	private CoreModule core;
	private IModule owner;

	DiceRoll(CoreModule core, IModule owner) {
		this.core = core;
		this.owner = owner;
	}

	@Override
	public boolean execute(MessageReceivedEvent event) {
		Random rng = new Random();
		int result = rng.nextInt(6) + 1;
		Embed.EmbedField embedField = new Embed.EmbedField("Result", String.valueOf(result), true);
		return core.getUtils().sendEmbed(event.getChannel(), "Dice Roll", owner.getName(), core.botDefColor, embedField);
	}
}
