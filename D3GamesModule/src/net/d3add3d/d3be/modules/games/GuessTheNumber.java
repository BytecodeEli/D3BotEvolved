package net.d3add3d.d3be.modules.games;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.modules.IModule;

import java.awt.*;
import java.util.Random;

public class GuessTheNumber implements ICommandExecutor {
	private CoreModule core;
	private IModule owner;

	GuessTheNumber(CoreModule core, IModule owner) {
		this.core = core;
		this.owner = owner;
	}

	@Override
	public boolean execute(MessageReceivedEvent event) {
		Random rng = new Random();
		int number = rng.nextInt(101);
		Embed.EmbedField embedField1, embedField2;
		String[] msg = event.getMessage().getContent().split(" ");
		try {
			int guess = Integer.parseInt(msg[1]);
			if(guess == number) {
				embedField1 = new Embed.EmbedField("Result", "You win!", true);
			} else {
				embedField1 = new Embed.EmbedField("Result", "Nope, try again!", true);
			}
			embedField2 = new Embed.EmbedField("The number was",String.valueOf(number), true);
			return core.getUtils().sendEmbed(event.getChannel(), "Guess The Number", owner.getName(), core.botDefColor, embedField1, embedField2);
		} catch(NumberFormatException e) {
			embedField1 = new Embed.EmbedField("Error", "An error has occured parsing your guess, are you sure it is a valid number?", true);
			return core.getUtils().sendEmbed(event.getChannel(), "Guess The Number", owner.getName(), new Color(255,255,0), embedField1);
		}
	}
}
