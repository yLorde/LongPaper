package br.com.ylorde.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class PlayerChat implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChat(@NotNull PlayerChatEvent event) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        Player player = event.getPlayer();

        if (plugin.getConfig().getBoolean("events.playerChat.customChat.enabled")) {

        }
    }
}
