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
		IEmbed.IEmbedField embedField = new Embed.EmbedField("Result", String.valueOf(result), true);
		return core.getUtils().sendEmbed(event.getChannel(), "Dice Roll", owner.getName(), false, embedField);
	}
}
