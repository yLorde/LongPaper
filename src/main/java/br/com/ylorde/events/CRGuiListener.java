package br.com.ylorde.events;

import br.com.ylorde.holders.CRIFurnace;
import br.com.ylorde.utils.ConvertToColoredText;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class CRGuiListener implements Listener {
    @EventHandler
    public void onClick(@NotNull InventoryClickEvent e) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        ConvertToColoredText convertToColoredText = new ConvertToColoredText();

        if (e.getView().getTopInventory().getHolder() instanceof CRIFurnace) {
            int slot = e.getSlot();
            if (plugin.getConfig().getString("commands.customRecipes.furnace.list."+slot) != null) {
                String material = plugin.getConfig().getString("commands.customRecipes.furnace.list."+slot+".material");
                assert material != null;

                String name = plugin.getConfig().getString("commands.customRecipes.furnace.list."+slot+".name");
                assert name != null;

                String input = plugin.getConfig().getString("commands.customRecipes.furnace.list."+slot+".recipe.input");
                assert input != null;

                String output = plugin.getConfig().getString("commands.customRecipes.furnace.list."+slot+".recipe.output");
                assert output != null;

                Player player = (Player) e.getWhoClicked();
                player.sendMessage(convertToColoredText.convert(name));

                player.sendMessage("Input >> "+input);
                player.sendMessage("Output >> "+output);
            }

            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrag(@NotNull InventoryDragEvent e) {
        if (e.getView().getTopInventory().getHolder() instanceof CRIFurnace) {
            e.setCancelled(true);
        }
    }
}
