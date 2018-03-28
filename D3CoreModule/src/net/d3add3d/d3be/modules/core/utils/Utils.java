package net.d3add3d.d3be.modules.core.utils;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IEmbed;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.EmbedBuilder;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.PermissionUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Utils {

	private IDiscordClient client;

	public Utils(IDiscordClient client) {
		this.client = client;
	}

	public boolean sendEmbed(IChannel chan, String funcName, String moduleName, Color color, Embed.EmbedField... fields) {
		ArrayList<Embed.EmbedField> temp = new ArrayList<>(Arrays.asList(fields));
		return sendEmbed(chan, funcName, moduleName, color, temp);
	}

	public boolean sendEmbed(IChannel chan, String funcName, String moduleName, Color color, ArrayList<Embed.EmbedField> embeds) {
		if(PermissionUtils.hasPermissions(chan, this.client.getOurUser(), Permissions.SEND_MESSAGES)) {
			EmbedBuilder embedBuilder = new EmbedBuilder();
			embedBuilder.withAuthorName(funcName);
			embedBuilder.withColor(color);
			embeds.forEach(embedBuilder::appendField);
			embedBuilder.withFooterText(moduleName + " Module");
			MessageBuilder messageBuilder = new MessageBuilder(this.client);
			messageBuilder.withChannel(chan);
			messageBuilder.withEmbed(embedBuilder.build());
			try {
				messageBuilder.send();
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;

		} else {
			return false;
		}
	}

	public boolean sendMessage(IChannel chan, String msg) {
		if(PermissionUtils.hasPermissions(chan, this.client.getOurUser(), Permissions.SEND_MESSAGES)) {
			MessageBuilder messageBuilder = new MessageBuilder(this.client);
			messageBuilder.withChannel(chan).withContent(msg);
			try {
				messageBuilder.send();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

}
