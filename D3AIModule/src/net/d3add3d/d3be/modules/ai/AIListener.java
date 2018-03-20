package net.d3add3d.d3be.modules.ai;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.MessageBuilder;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project D3BotEvolved
 * Package net.d3add3d.d3be.modules.ai
 * Created by D3add3d on 6. 2. 2018.
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
public class AIListener {

	private String[] matchingPatternsArray = new String[]{"i need a hug", "hug me", "i will cry"};
	private String[] matchingBlacklistArray = new String[]{"she", "he", "they", "it"};
	private ArrayList<String> matchingPatterns = new ArrayList<>(Arrays.asList(matchingPatternsArray));
	private ArrayList<String> matchingBlacklist = new ArrayList<>(Arrays.asList(matchingBlacklistArray));

	private AIModule aiModule;

	AIListener(AIModule aiModule) {
		this.aiModule = aiModule;
	}

	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		aiModule.init();
	}

	@EventSubscriber
	public void onMessageReceived(MessageReceivedEvent event) {
		String content = event.getMessage().getContent();
		boolean isMatch = matchingPatterns.stream().anyMatch(a -> content.contains(a) && matchingBlacklist.stream().noneMatch(content::contains));
		if(isMatch) {
			System.out.println("AI Match");
			MessageBuilder builder = new MessageBuilder(event.getClient());
			builder.withChannel(event.getChannel()).withContent("https://www.tenor.co/vfFB.gif").send();
		}
	}

}
