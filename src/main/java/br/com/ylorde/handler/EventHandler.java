package br.com.ylorde.handler;

import br.com.ylorde.Main;
import br.com.ylorde.events.CRGuiListener;
import br.com.ylorde.events.PlayerChat;
import br.com.ylorde.events.PlayerLogin;

public record EventHandler(Main plugin) {
    public void handle() {
        this.plugin.getServer().getPluginManager().registerEvents(new PlayerLogin(), this.plugin);
        this.plugin.getServer().getPluginManager().registerEvents(new PlayerChat(), this.plugin);
        this.plugin.getServer().getPluginManager().registerEvents(new CRGuiListener(), this.plugin);
    }
}
