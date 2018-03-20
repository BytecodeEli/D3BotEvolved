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

import java.util.ArrayList;

public class Utils {

	private IDiscordClient client;

	public Utils(IDiscordClient client) {
		this.client = client;
	}

	public boolean sendEmbed(IChannel chan, String funcName, String moduleName, boolean isError, IEmbed.IEmbedField... fields) {
		if(PermissionUtils.hasPermissions(chan, this.client.getOurUser(), Permissions.SEND_MESSAGES)) {
			EmbedBuilder embedBuilder = new EmbedBuilder();
			embedBuilder.withAuthorName(funcName);
			if(isError) {
				embedBuilder.withColor(255,0,0);
			} else {
				embedBuilder.withColor(0,255,255);
			}
			for(IEmbed.IEmbedField field : fields) {
				embedBuilder.appendField(field);
			}
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

	public boolean sendEmbed(IChannel chan, String funcName, String moduleName, boolean isError, ArrayList<Embed.EmbedField> embeds) {
		if(PermissionUtils.hasPermissions(chan, this.client.getOurUser(), Permissions.SEND_MESSAGES)) {
			EmbedBuilder embedBuilder = new EmbedBuilder();
			embedBuilder.withAuthorName(funcName);
			if(isError) {
				embedBuilder.withColor(255,0,0);
			} else {
				embedBuilder.withColor(0,255,255);
			}
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
