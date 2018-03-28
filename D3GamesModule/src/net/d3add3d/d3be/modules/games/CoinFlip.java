package net.d3add3d.d3be.modules.games;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.modules.IModule;

import java.awt.*;
import java.util.Random;

public class CoinFlip implements ICommandExecutor {

	private CoreModule core;
	private IModule owner;

	CoinFlip(CoreModule core, IModule owner) {
		this.core = core;
		this.owner = owner;
	}

	@Override
	public boolean execute(MessageReceivedEvent event) {
		Random rng = new Random();
		boolean result = rng.nextBoolean();
		Embed.EmbedField embedField;
		if(result) {
			embedField = new Embed.EmbedField("Result", "Heads", true);
		} else {
			embedField = new Embed.EmbedField("Result", "Tails", true);
		}
		return core.getUtils().sendEmbed(event.getChannel(), "Coin Flip", owner.getName(), core.botDefColor, embedField);
	}
}
