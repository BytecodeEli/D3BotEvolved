package net.d3add3d.d3be.modules.mod;

import net.d3add3d.d3be.modules.core.CoreModule;
import net.d3add3d.d3be.modules.core.ModuleHandler;
import net.d3add3d.d3be.modules.core.ModuleObj;
import org.dizitart.no2.NitriteCollection;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.modules.IModule;
import sx.blah.discord.modules.Requires;

import java.util.List;


@Requires("net.d3add3d.d3be.modules.core.CoreModule")
public class ModModule implements IModule {

    private IDiscordClient client;
    private ModListener listener;
    private CoreModule core;
    private ModuleHandler moduleHandler;
    private ModuleObj moduleObj;
    private NitriteCollection collection;

    private boolean isRegistered;


    //TODO: OWN COLLECTION TO STORE SETTINGS AND DATA

    @Override
    public boolean enable(IDiscordClient iDiscordClient) {
        client = iDiscordClient;
        listener = new ModListener(this);
        if(client.getModuleLoader() != null) {
            init();
        } else {
            client.getDispatcher().registerListener(listener);
            isRegistered = true;
        }
        return true;
    }

    public void init() {
        List<IModule> modules = client.getModuleLoader().getLoadedModules();
        for(IModule module : modules) {
            if(module instanceof CoreModule) {
                core = (CoreModule) module;
                break;
            }
        }
        try {
            collection = core.getDb().getCollection("mod");
        } catch (Exception e) {
            e.printStackTrace();
        }
        moduleHandler = core.getModuleHandler();
        this.moduleObj = new ModuleObj(getName());
        this.moduleObj.addCommand(new ModCommand(new Warn(this.core, this)).setKeyword("warn").setDescription("Warns a user.").setFormat("%s @mention reason"));
        this.moduleObj.setAvailable(true);
        moduleHandler.registerModule(this.moduleObj);

        System.out.println("MOD ENABLED");
    }

    @Override
    public void disable() {
        if(isRegistered) {
            client.getDispatcher().unregisterListener(listener);
            isRegistered = false;
        }
        moduleHandler.unregisterModule(this.moduleObj);
        System.out.println("MOD DISABLED");
    }

    @Override
    public String getName() {
        return "Mod";
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
        return "2.9.3";
    }
}
