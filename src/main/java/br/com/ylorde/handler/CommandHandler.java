package br.com.ylorde.handler;

import br.com.ylorde.Main;
import br.com.ylorde.handler.commands.AquecerCMD;
import br.com.ylorde.handler.commands.LanternaCMD;
import br.com.ylorde.handler.commands.LixeiraCMD;
import br.com.ylorde.handler.commands.ReloadConfigCMD;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public record CommandHandler(Main plugin) {
    public void handle() {
        this.plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            if (plugin.getConfig().getBoolean("commands.lanterna.enabled"))
                commands.registrar().register(new LanternaCMD(plugin).build());

            if (plugin.getConfig().getBoolean("commands.reloadConfig.enabled"))
                commands.registrar().register(new ReloadConfigCMD(plugin).buid());

            if (plugin.getConfig().getBoolean("commands.aquecer.enabled"))
                commands.registrar().register(new AquecerCMD(plugin).build());

            if (plugin.getConfig().getBoolean("commands.lixeira.enabled"))
                commands.registrar().register(new LixeiraCMD(plugin).build());
        });
    }
}
