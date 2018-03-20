package net.d3add3d.d3be.modules.core;

import net.d3add3d.d3be.modules.core.utils.Utils;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.modules.IModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CoreModule implements IModule {

	private EventDispatcher dispatcher;
	private CoreListener listener;
	private IDiscordClient client;
	private ModuleHandler handler;
	private Utils utils;
	public HashMap<String, Object> defaults = new HashMap<>();
	private Nitrite db;

	@Override
	public boolean enable(IDiscordClient iDiscordClient) {
		this.db = Nitrite.builder().compressed().filePath("database.db").openOrCreate("d3be", "1!h0uR$2AmiNut€5");
		this.client = iDiscordClient;
		this.dispatcher = this.client.getDispatcher();
		this.listener = new CoreListener(this);
		this.dispatcher.registerListener(this.listener);
		this.handler = new ModuleHandler(this);
		this.utils = new Utils(this.client);
		defaults.put("owner", "139381119622578177");
		defaults.put("prefix", "++");
		System.out.println("CORE ENABLED");
		return true;
	}

	@Override
	public void disable() {
		//CORE MODULE SHOULD NEVER BE UNLOADED!
		System.err.println("*****CORE UNLOADED - IF THE BOT CONTINUES TO WORK AFTER THIS IT IS PROBABLY HORRIBLY BROKEN*****");
		this.dispatcher.unregisterListener(this.listener);
	}

	@Override
	public String getName() {
		return "Core";
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

	public ModuleHandler getModuleHandler() {
		return this.handler;
	}

	public Utils getUtils() { return  this.utils; }

	public void cleanClose() {
		db.close();
	}

	public Nitrite getDb() throws Exception {
		if(!db.isClosed()) {
			return this.db;
		} else {
			throw(new Exception("NITRITE DB IS CLOSED", new Throwable()));
		}
	}
}
