package net.d3add3d.d3be.modules.ai;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ModuleHandler;
import net.d3add3d.d3be.modules.core.ModuleObj;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;
import sx.blah.discord.modules.Requires;

import java.util.List;

@Requires("net.d3add3d.d3be.modules.core.CoreModule")
public class AIModule implements IModule {

	private ModuleObj moduleObj;
	private CoreModule core;
	private ModuleHandler moduleHandler;
	private IDiscordClient client;
	private AIListener listener;
	private boolean isRegistered = false;

	@Override
	public boolean enable(IDiscordClient iDiscordClient) {
		client = iDiscordClient;
		listener = new AIListener(this);
		if(client.getModuleLoader() != null) {
			init();
		} else {
			client.getDispatcher().registerListener(listener);
			isRegistered = true;
		}
		return true;
	}

	void init() {
		List<IModule> modules = client.getModuleLoader().getLoadedModules();
		for(IModule module : modules) {
			if(module instanceof CoreModule) {
				core = (CoreModule) module;
				break;
			}
		}
		moduleHandler = core.getModuleHandler();
		this.moduleObj = new ModuleObj(getName());
		this.moduleObj.setAvailable(true);
		moduleHandler.registerModule(this.moduleObj);

		System.out.println("AI ENABLED");
	}

	@Override
	public void disable() {
		if(isRegistered) {
			client.getDispatcher().unregisterListener(listener);
			isRegistered = false;
		}
		moduleHandler.unregisterModule(this.moduleObj);
		System.out.println("AI DISABLED");
	}

	@Override
	public String getName() {
		return "AI";
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
