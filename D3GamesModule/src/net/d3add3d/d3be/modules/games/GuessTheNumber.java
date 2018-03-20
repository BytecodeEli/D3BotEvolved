package net.d3add3d.d3be.modules.games;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ICommandExecutor;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.modules.IModule;

import java.util.Random;

/**
 * Project D3BotEvolved
 * Package net.d3add3d.d3be.modules.games
 * Created by D3add3d on 22. 1. 2018.
 * <p>
 * Copyright (C) 2018 D3add3d
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
		IEmbed.IEmbedField embedField1, embedField2;
		String[] msg = event.getMessage().getContent().split(" ");
		try {
			int guess = Integer.parseInt(msg[1]);
			if(guess == number) {
				embedField1 = new Embed.EmbedField("Result", "You win!", true);
			} else {
				embedField1 = new Embed.EmbedField("Result", "Nope, try again!", true);
			}
			embedField2 = new Embed.EmbedField("The number was",String.valueOf(number), true);
			return core.getUtils().sendEmbed(event.getChannel(), "Guess The Number", owner.getName(), false, embedField1, embedField2);
		} catch(NumberFormatException e) {
			embedField1 = new Embed.EmbedField("Error", "An error has occured parsing your guess, are you sure it is a valid number?", true);
			return core.getUtils().sendEmbed(event.getChannel(), "Guess The Number", owner.getName(), true, embedField1);
		}
	}
}
