package net.d3add3d.d3be.modules.core;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.obj.Embed;
import sx.blah.discord.handle.obj.IGuild;

import java.util.ArrayList;

public class ModuleHandler {

	private ArrayList<ModuleObj> modules;
	private CoreModule core;

	ModuleHandler(CoreModule ref) {
		modules = new ArrayList<>();
		core = ref;
	}

	public void registerModule(ModuleObj module) {
		modules.add(module);
		System.out.println(module.getName() + " registered");
	}

	public void unregisterModule(ModuleObj module) {
		modules.remove(module);
		System.out.println(module.getName() + " unregistered");
	}

	void dispatchEvent(Event event) {
		MessageReceivedEvent msgE;
		if(event instanceof MessageReceivedEvent) {
			msgE = (MessageReceivedEvent) event;

			for (ModuleObj module : modules) {
				for (Command command : module.getCommands()) {
					if (msgE.getMessage().getContent().split(" ")[0].substring(2).equalsIgnoreCase(command.getKeyword()) || command.getAliases().contains(msgE.getMessage().getContent().split(" ")[0].substring(2).toLowerCase())) {
							command.executor.execute(msgE);
					}
				}
			}
		}
	}

	void displayHelp(Event event) {
		MessageReceivedEvent msgE;
		if(event instanceof MessageReceivedEvent) {
			msgE = (MessageReceivedEvent) event;

			ArrayList<Embed.EmbedField> embeds = new ArrayList<>();
			embeds.add(new Embed.EmbedField("Prefix", "```" + core.defaults.get("prefix")  + "```", true));
			embeds.add(new Embed.EmbedField("Legend", "```<> - required\n[] - range\n() - optional```", true));
			for(ModuleObj module : modules) {
				StringBuilder sb = new StringBuilder();
				if (module.getCommands().size() > 0) {
					sb.append("```");
					for (Command command : module.getCommands()) {
						sb.append(String.format(command.getFormat(), command.getKeyword()));
						sb.append(" - ");
						sb.append(command.getDescription());
						sb.append("\n");
					}
					sb.append("```");
					embeds.add(new Embed.EmbedField(module.getName() + " Module", sb.toString(), false));
				}
			}
			StringBuilder bldr = new StringBuilder();
			modules.forEach(m -> bldr.append(m.getName()).append(", "));
			embeds.add(2, new Embed.EmbedField("Loaded Modules", bldr.toString().substring(0, bldr.length() > 2 ? bldr.length()-2 : bldr.length()).length() > 0 ? bldr.toString().substring(0, bldr.length() > 2 ? bldr.length()-2 : bldr.length()) : "None", true));
			core.getUtils().sendEmbed(msgE.getChannel(), "Help", core.getName(), false, embeds);
		}
	}

	void dumpDebugInfo(Event event) {
		MessageReceivedEvent msgE;
		if(event instanceof MessageReceivedEvent) {
			msgE = (MessageReceivedEvent) event;
			ArrayList<Embed.EmbedField> embeds = new ArrayList<>();
			StringBuilder modulesList = new StringBuilder();
			for(ModuleObj module : modules) {
				modulesList.append(module.getName());
				modulesList.append(", ");
			}
			StringBuilder guildsList = new StringBuilder();
			for(IGuild guild : event.getClient().getGuilds()) {
				guildsList.append(guild.getName());
				guildsList.append(", ");
			}
			embeds.add(new Embed.EmbedField("modules", "```" + modulesList.toString().substring(0, modulesList.length()-2) + "```", false));
			embeds.add(new Embed.EmbedField("guilds", "```" + guildsList.toString().substring(0, guildsList.length()-2) + "```", false));
			core.getUtils().sendEmbed(msgE.getChannel(), "Debug info", core.getName(), true, embeds);
		}
	}

	private boolean isOwner(MessageReceivedEvent msgE) {
		return msgE.getAuthor().getStringID().equals(core.defaults.get("owner"));
	}

	ArrayList<ModuleObj> getModulesCopy() {
		return new ArrayList<>(modules);
	}

}
