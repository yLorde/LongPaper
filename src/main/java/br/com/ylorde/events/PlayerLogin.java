package br.com.ylorde.events;

import br.com.ylorde.utils.ConvertToColoredText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerLogin implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        Player player = event.getPlayer();

        ConvertToColoredText convertToColoredText = new ConvertToColoredText();

        if (plugin.getConfig().getBoolean("events.playerJoin.message.enabled")) {
            if (plugin.getConfig().getBoolean("events.playerJoin.message.customMessage.enabled")) {
                event.setJoinMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("events.playerJoin.message.customMessage.message"))
                                .replace("%player_nickname", player.getDisplayName())
                ));
            }
        } else {
            event.setJoinMessage("");
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        Player player = event.getPlayer();

        ConvertToColoredText convertToColoredText = new ConvertToColoredText();

        if (plugin.getConfig().getBoolean("events.playerQuit.message.enabled")) {
            if (plugin.getConfig().getBoolean("events.playerQuit.message.customMessage.enabled")) {
                event.setQuitMessage(convertToColoredText.convert(
                        Objects.requireNonNull(plugin.getConfig().getString("events.playerQuit.message.customMessage.message"))
                                .replace("%player_nickname", player.getDisplayName())
                ));
            }
        } else {
            event.setQuitMessage("");
        }
    }
}
