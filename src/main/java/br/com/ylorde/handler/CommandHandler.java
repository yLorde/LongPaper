package br.com.ylorde.handler;

import br.com.ylorde.Main;
import br.com.ylorde.handler.commands.admin.ReloadConfigCMD;
import br.com.ylorde.handler.commands.player.AquecerCMD;
import br.com.ylorde.handler.commands.player.CustomRecipesCMD;
import br.com.ylorde.handler.commands.player.LanternaCMD;
import br.com.ylorde.handler.commands.player.LixeiraCMD;
import br.com.ylorde.handler.commands.utils.BigornaCMD;
import br.com.ylorde.handler.commands.utils.CraftCMD;
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

            if (plugin.getConfig().getBoolean("commands.customRecipes.enabled"))
                commands.registrar().register(new CustomRecipesCMD(plugin).build());

            if (plugin.getConfig().getBoolean("commands.craft.enabled"))
                commands.registrar().register(new CraftCMD(plugin).build());

            if (plugin.getConfig().getBoolean("commands.bigorna.enabled"))
                commands.registrar().register(new BigornaCMD(plugin).build());
        });
    }
}
