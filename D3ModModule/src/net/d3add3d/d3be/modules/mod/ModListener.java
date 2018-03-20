package net.d3add3d.d3be.modules.mod;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;

public class ModListener {

    private ModModule modModule;

    ModListener(ModModule modModule) {
        this.modModule = modModule;
    }

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event) {
        modModule.init();
    }

}
