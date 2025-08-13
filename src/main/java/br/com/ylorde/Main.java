package br.com.ylorde;

import br.com.ylorde.handler.CommandHandler;
import br.com.ylorde.handler.EventHandler;
import br.com.ylorde.utils.Console;
import br.com.ylorde.utils.SendHelloMessage;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveResource("config.yml", false);
        saveDefaultConfig();

        Console console = new Console();

        console.sendColoredMessage("&aLongPaper is now &l&aOnline &a:)");
        console.sendColoredMessage("&aMade by: &a&l"+this.getDescription().getAuthors().getFirst() + " &aand &a&l"+this.getDescription().getAuthors().getLast());

        if (!getConfig().getBoolean("plugin.disableHelloMessage")) {
            new SendHelloMessage().send();
        }

        new CommandHandler(this).handle();
        new EventHandler(this).handle();
    }

    @Override
    public void onDisable() {
        new Console().sendColoredMessage("&cLongPager is now &lOffline &c:(");
    }

    @Override
    public void reloadConfig() {
        super.reloadConfig();
    }
}
