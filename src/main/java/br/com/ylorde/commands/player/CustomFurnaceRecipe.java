package br.com.ylorde.commands.player;

import br.com.ylorde.holders.CRIFurnace;
import br.com.ylorde.utils.Console;
import br.com.ylorde.utils.ConvertToColoredText;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomFurnaceRecipe {
    public static int execute(@NotNull CommandContext<CommandSourceStack> ctx) {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("LongPaper");
        assert plugin != null;

        CommandSender sender = ctx.getSource().getSender();
        Entity executor = ctx.getSource().getExecutor();

        Console console = new Console();
        ConvertToColoredText convertToColoredText = new ConvertToColoredText();

        if (!(executor instanceof Player player)) {
            sender.sendRichMessage("<red><bold>ERRO</bold></red><yellow> Apenas jogadores podem usar esse comando!</yellow>");
            return Command.SINGLE_SUCCESS;
        }

        if (sender == executor) {
            List<String> list = new ArrayList<>();

            for (int i = 0; i < 999999; i++) {
                String material = plugin.getConfig().getString("commands.customRecipes.furnace.list." + String.valueOf(i) + ".material");
                if (material == null) {
                    break;
                } else {
                    list.add(String.valueOf(i));
                }
            }

            int slots = 9;
            if (list.size() > 10) slots = 18;
            if (list.size() > 18) slots = 27;
            if (list.size() > 27) slots = 36;
            if (list.size() > 36) slots = 45;
            if (list.size() > 45) slots = 54;

            CRIFurnace gui = new CRIFurnace(slots, Objects.requireNonNull(plugin.getConfig().getString("commands.customRecipes.furnace.interfaceTitle")));
            Inventory menu = gui.getInventory();

            for (int i = 0; i < list.size() + 1; i++) {
                String materialA = plugin.getConfig().getString("commands.customRecipes.furnace.list." + i + ".material");
                if (materialA == null) continue; // pula se não tiver material

                ItemStack item = new ItemStack(Material.valueOf(materialA), 1);
                ItemMeta meta = item.getItemMeta();

                if (meta != null) {
                    String name = plugin.getConfig().getString("commands.customRecipes.furnace.list." + i + ".name");
                    if (name != null) {
                        meta.setDisplayName(convertToColoredText.convert(name));
                    }

                    List<String> lore = plugin.getConfig().getStringList("commands.customRecipes.furnace.list." + i + ".lore");
                    if (!lore.isEmpty()) {
                        List<String> coloredLore = lore.stream()
                                .map(convertToColoredText::convert)
                                .toList();
                        meta.setLore(coloredLore);
                    }

                    item.setItemMeta(meta);
                }

                menu.addItem(item);
            }

            player.openInventory(menu);

            if (plugin.getConfig().getBoolean("commands.config.soundOnUse")) {
                player.playSound(player, Objects.requireNonNull(plugin.getConfig().getString("commands.config.openSound")), 1, 1);
            }
            player.sendMessage(convertToColoredText.convert(
                    Objects.requireNonNull(plugin.getConfig().getString("commands.customRecipes.furnace.onUseMessage"))
            ));
        }

        return Command.SINGLE_SUCCESS;
    }
}
