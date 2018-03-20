package net.d3add3d.d3be.modules.core;

import sx.blah.discord.api.events.Event;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.GuildCreateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.user.PresenceUpdateEvent;
import sx.blah.discord.handle.impl.events.user.UserUpdateEvent;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.modules.IModule;
import sx.blah.discord.modules.ModuleLoader;

import java.util.ArrayList;
import java.util.List;

public class CoreListener {

	private ModuleLoader loader;
	private IModule gameModule;
	private CoreModule coreModule;

	private ArrayList<GuildCreateEvent> guildQueue = new ArrayList<>();

	CoreListener(CoreModule coreModule) {
		this.coreModule = coreModule;
	}

	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		String msg = event.getMessage().getContent();
		String author = event.getMessage().getAuthor().getName();
		System.out.println("[" + (event.getGuild() != null ? event.getGuild().getName() : "DIRECT") + ">#" + (event.getChannel() != null ? event.getChannel().getName() : "!!NOT A CHANNEL!!") + "] " + author + ": " + msg);
		if(msg.startsWith(coreModule.defaults.get("prefix").toString())) {

			String[] tokens = msg.substring(2).split(" ");

			if(event.getAuthor().getStringID().equals(coreModule.defaults.get("owner"))) {
				switch(tokens[0]) {

					case "stop":
						coreModule.getUtils().sendMessage(event.getChannel(), "Stopping...");
						System.out.println("Stopping...");
						event.getClient().online("!help");
						coreModule.cleanClose();
						event.getClient().logout();
						return;

					case "load":
						return;

					case "unload":
						return;

					case "loadexternal":
						return;

					case "dump":
						coreModule.getModuleHandler().dumpDebugInfo(event);
						return;

					case "reinit":
						coreModule.getUtils().sendMessage(event.getChannel(), "Reinit...");
						event.getClient().online("!help | ++help");
						return;
				}
			}

			if (tokens[0].startsWith("help")) {
				coreModule.getModuleHandler().displayHelp(event);
			} else {
				coreModule.getModuleHandler().dispatchEvent(event);
			}
		}
	}

	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		event.getClient().online("!help | ++help");
		loader = event.getClient().getModuleLoader();
		List<IModule> modules = loader.getLoadedModules();
		gameModule = null;
		for(IModule module:modules) {
			if(module.getName().equalsIgnoreCase("games")) {
				gameModule = module;
				break;
			}
		}
	}

	@EventSubscriber
	public void onGuildCreateEvent(GuildCreateEvent event) {
		guildQueue.add(event);
		IGuild guild = event.getGuild();
		System.out.println("Guild received: " + guild.getStringID() + " " + guild.getName());
	}

	@EventSubscriber
	public void onAnyEvent(Event event) {
		StringBuilder builder = new StringBuilder();
		builder.append(event.getClass().getSimpleName());
		switch(event.getClass().getSimpleName()) {
			case "PresenceUpdateEvent":
				PresenceUpdateEvent pue = (PresenceUpdateEvent) event;
				builder.append(" ");
				builder.append(pue.getUser().getName());
				builder.append(" is now ");
				builder.append(pue.getNewPresence().getStatus().toString());
				break;
			case "UserUpdateEvent":
				UserUpdateEvent uue = (UserUpdateEvent) event;
				builder.append(" ");
				builder.append(uue.getUser().getName());
				break;
			default:
				break;
		}
		System.out.println("[DBG] " + builder.toString());

	}

}
