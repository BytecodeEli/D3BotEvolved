package net.d3add3d.d3be.modules.games;

import net.d3add3d.d3be.modules.core.*;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;
import sx.blah.discord.modules.Requires;

import java.util.List;

@Requires("net.d3add3d.d3be.modules.core.CoreModule")
public class GamesModule implements IModule {

	private ModuleObj moduleObj;
	private CoreModule core;
	private ModuleHandler moduleHandler;
	private IDiscordClient client;
	private GamesListener listener;
	private boolean isRegistered = false;

	@Override
	public boolean enable(IDiscordClient iDiscordClient) {
		client = iDiscordClient;
		listener = new GamesListener(this);
		if(client.getModuleLoader() != null) {
			init();
		} else {
			client.getDispatcher().registerListener(listener);
			isRegistered = true;
		}
		return true;
	}

	void init() { //We can't use client.getModuleLoader() in enable() so we wait until the client is ready, see GamesListener
		List<IModule> modules = client.getModuleLoader().getLoadedModules();
		for(IModule module : modules) {
			if(module instanceof CoreModule) {
				core = (CoreModule) module;
				break;
			}
		}
		moduleHandler = core.getModuleHandler();
		this.moduleObj = new ModuleObj(getName());
		this.moduleObj.addCommand(new GamesCommand(new CoinFlip(core, this)).setKeyword("coin").addAlias("coinflip").setDescription("Simple coin flip.").setFormat("%s"));
		this.moduleObj.addCommand(new GamesCommand(new RockPaperScissors(core, this)).setKeyword("rps").addAlias("rockpaperscissors").setDescription("Play rock-paper-scissors with the bot!").setLongDescription("Awards XP when RPG module is available.").setFormat("%s <r/p/s>"));
		this.moduleObj.addCommand(new GamesCommand(new DiceRoll(core, this)).setKeyword("dice").addAlias("diceroll").setDescription("Simple dice roll.").setFormat("%s"));
		this.moduleObj.addCommand(new GamesCommand(new GuessTheNumber(core, this)).setKeyword("guess").addAlias("guessthenumber").setDescription("Guess the number game.").setLongDescription("Awards coins when Casino module is available and XP when RPG module is available.").setFormat("%s <[0-100]>"));
		this.moduleObj.setAvailable(true);
		moduleHandler.registerModule(this.moduleObj);

		System.out.println("GAMES ENABLED");
	}

	@Override
	public void disable() {
		if(isRegistered) {
			client.getDispatcher().unregisterListener(listener);
			isRegistered = false;
		}
		moduleHandler.unregisterModule(this.moduleObj);
		System.out.println("GAMES DISABLED");
	}

	@Override
	public String getName() {
		return "Games";
	}

	@Override
	public String getAuthor() {
		return "D3add3d";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getMinimumDiscord4JVersion() {
		return "2.9.2";
	}
}
